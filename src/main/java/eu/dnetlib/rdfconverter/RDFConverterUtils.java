package eu.dnetlib.rdfconverter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jsonldjava.core.JsonLdOptions;
import com.github.jsonldjava.core.JsonLdProcessor;
import com.github.jsonldjava.utils.JsonUtils;
import eu.dnetlib.rdfconverter.model.BioSchemaProteinModel;
import eu.dnetlib.rdfconverter.model.DataciteProteinModel;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.RDFWriter;
import org.eclipse.rdf4j.rio.Rio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

@Component
public class RDFConverterUtils {

	private static final Logger log = LoggerFactory.getLogger(RDFConverterUtils.class);

	public void nQuads2DataciteJson(File inputDir, File outputDir, boolean jsonLDDump) {
		File[] inputFiles = inputDir.listFiles();
		Arrays.stream(inputFiles).forEach(f -> {
			try {
				nQuadsFile2DataciteJson(f, outputDir, jsonLDDump);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
	}

	private void nQuadsFile2DataciteJson(File inputFile, File outputDir, boolean jsonLDDump) throws IOException {
		log.debug("Parsing "+inputFile);
		InputStream in = new  FileInputStream(inputFile);
		Model model = Rio.parse(in, "", RDFFormat.NQUADS);
		StringWriter jsonLDWriter = new StringWriter();
		RDFWriter rdfRecordWriter = Rio.createWriter(RDFFormat.JSONLD, jsonLDWriter);
		Rio.write(model, rdfRecordWriter);
		String jsonLDBuffer = jsonLDWriter.toString();

		if (jsonLDDump) {
			System.out.println(jsonLDBuffer);
		}

		Object jsonObject = JsonUtils.fromString(jsonLDBuffer);
		Object compact = JsonLdProcessor.compact(jsonObject, new HashMap<>(), new JsonLdOptions());
		String compactContent = JsonUtils.toString(compact);

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
		objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		BioSchemaProteinModel bioSchemaProteinModel = objectMapper.readValue(compactContent, BioSchemaProteinModel.class);
		log.debug("BioSchema id: "+bioSchemaProteinModel.getId());

		Optional<BioSchemaProteinModel.Entry> retrievedOnEntry = bioSchemaProteinModel.getEntryList().stream().filter(entry -> {
			return entry.getRetrievedOn() != null;
		}).findFirst();

		bioSchemaProteinModel.getEntryList().stream().forEach(entry -> {

			if (entry.getType() != null && entry.getType().stream().filter(type -> type.equals("https://schema.org/Protein")).count()==1) {

				DataciteProteinModel dataciteProteinModel = new DataciteProteinModel();

				DataciteProteinModel.Types types = new DataciteProteinModel.Types();
				types.setResourceType("Protein");
				types.setResourceTypeGeneral("Dataset");
				dataciteProteinModel.setTypes(types);

				if (retrievedOnEntry.isPresent()) {
					String retrievedOn = retrievedOnEntry.get().getRetrievedOn().get(0).getValue();
					log.debug("RetrievedOn: "+retrievedOn);
					DataciteProteinModel.DataciteDate dataciteDate = new DataciteProteinModel.DataciteDate();
					dataciteDate.setDate(retrievedOn);
					dataciteDate.setDateType("Collected");
					dataciteProteinModel.getDates().add(dataciteDate);
				}

				if (entry.getName() != null) {
					log.debug("Name: "+entry.getName());
					DataciteProteinModel.Title title = new DataciteProteinModel.Title();
					title.setTitle(entry.getName());
					dataciteProteinModel.getTitles().add(title);
				}
				DataciteProteinModel.Identifier identifier = new DataciteProteinModel.Identifier();
				log.debug("Id: "+entry.getId());
				identifier.setIdentifier(entry.getId());
				identifier.setIdentifierType("URL");
				dataciteProteinModel.getIdentifiers().add(identifier);

				if (entry.getIdentifier()!=null) {
					log.debug("Identifier: "+entry.getIdentifier());
					addAlternateIdentifier(dataciteProteinModel, entry.getIdentifier());
				}

				if (entry.getDescription()!=null) {
					log.debug("description: "+entry.getDescription());
					DataciteProteinModel.Description description = new DataciteProteinModel.Description();
					description.setDescription(entry.getDescription());
					dataciteProteinModel.getDescriptions().add(description);
				}

				if (entry.getIsEncodedByBioChemEntity()!=null) {
					log.debug("isEncodedByBioChemEntity: "+entry.getIsEncodedByBioChemEntity());
					addRelatedIdentifier(dataciteProteinModel, entry.getIsEncodedByBioChemEntity(), "");
				}

				if (entry.getUrl()!=null) {
					log.debug("url: "+entry.getUrl());
					addAlternateIdentifier(dataciteProteinModel, entry.getUrl());
				}

				if (entry.getAlternateName()!=null) {
					log.debug("alternateName: "+entry.getAlternateName());
					DataciteProteinModel.Title title = new DataciteProteinModel.Title();
					title.setTitle(entry.getAlternateName());
					title.setTitleType("AlternativeTitle");
					dataciteProteinModel.getTitles().add(title);
				}

				if (entry.getBioChemInteraction()!=null){
					entry.getBioChemInteraction().stream().filter(Objects::nonNull).forEach(bc -> {
						log.debug("bioChemInteraction: " + bc.getId());
						addRelatedIdentifier(dataciteProteinModel, bc.getId(), "");
					});
				}

				if (entry.getBioChemSimilarity()!=null){
					entry.getBioChemSimilarity().stream().filter(Objects::nonNull).forEach(bc -> {
						log.debug("bioChemSimilarity: " + bc.getId());
						addRelatedIdentifier(dataciteProteinModel, bc.getId(), "");
					});
				}

				if (entry.getHasMolecularFunction()!=null) {
					log.debug("hasMolecularFunction: "+entry.getHasMolecularFunction());
					addRelatedIdentifier(dataciteProteinModel, entry.getHasMolecularFunction(), "");
				}

				if (entry.getIsInvolvedInBiologicalProcess()!=null) {
					log.debug("isInvolvedInBiologicalProcess: "+entry.getIsInvolvedInBiologicalProcess());
					addRelatedIdentifier(dataciteProteinModel, entry.getIsInvolvedInBiologicalProcess(), "");
				}

				if (entry.getIsEncodedByBioChemEntity()!=null) {
					log.debug("isEncodedByBioChemEntity: "+entry.getIsEncodedByBioChemEntity());
					addRelatedIdentifier(dataciteProteinModel, entry.getIsEncodedByBioChemEntity(), "");
				}

				if (entry.getIsPartOfBioChemEntity()!=null) {
					log.debug("isPartOfBioChemEntity: "+entry.getIsPartOfBioChemEntity());
					addRelatedIdentifier(dataciteProteinModel, entry.getIsPartOfBioChemEntity().getUrl(), "");
				}

				if (entry.getSameAs()!=null){
					entry.getSameAs().stream().filter(Objects::nonNull).forEach(sameAs -> {
						log.debug("sameAs: "+ sameAs.getId());
						addRelatedIdentifier(dataciteProteinModel, sameAs.getId(), "IsIdenticalTo");
					});
				}

				if (entry.getAssociatedDisease()!=null) {
					entry.getAssociatedDisease().stream().filter(Objects::nonNull).forEach(ad -> {
						log.debug("associated disease: "+ ad.getName());
						addRelatedIdentifier(dataciteProteinModel, ad.getName(), "IsIdenticalTo");
					});
				}

				ObjectMapper mapper = new ObjectMapper();
				try {
					String proteinId = "";
					try {
						String[] identifierParts = dataciteProteinModel.getIdentifiers().get(0).getIdentifier().split("/");
						proteinId = identifierParts[identifierParts.length - 1];
					} catch (Exception e) {
						log.warn("Identifier not found");
					}
					String inputName = inputFile.getName();
					String fp = "";
					if (!proteinId.isEmpty()) {
						fp = String.format("%s/%s.json", outputDir.getAbsolutePath(), proteinId.concat("["+inputName+"]"));
					}
					else {
						fp = String.format("%s/%s.json", outputDir.getAbsolutePath(), inputName);
					}
					mapper.writeValue(new File(fp), dataciteProteinModel);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		});
	}

	private void addRelatedIdentifier(DataciteProteinModel dataciteProteinModel, String relatedIdentifierValue, String relationType) {
		DataciteProteinModel.RelatedIdentifier relatedIdentifier = new DataciteProteinModel.RelatedIdentifier();
		relatedIdentifier.setRelatedIdentifier(relatedIdentifierValue);
		if (!relationType.isEmpty()) {
			relatedIdentifier.setRelationType(relationType);
		}
		dataciteProteinModel.getRelatedIdentifiers().add(relatedIdentifier);
	}

	private void addAlternateIdentifier(DataciteProteinModel dataciteProteinModel, String alternateIdentifierValue) {
		DataciteProteinModel.AlternateIdentifier alternateIdentifier = new DataciteProteinModel.AlternateIdentifier();
		alternateIdentifier.setAlternateIdentifier(alternateIdentifierValue);
		dataciteProteinModel.getAlternateIdentifiers().add(alternateIdentifier);
	}
}
