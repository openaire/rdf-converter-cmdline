package eu.dnetlib.rdfconverter.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BioSchemaProteinModel {
    @JsonProperty("@id")
    private String id;
    @JsonProperty("@graph")
    private List<Entry> entryList;

    public static class Entry {
        @JsonProperty("@id")
        private String id;
        @JsonProperty("@type")
        private List<String> type;
        @JsonProperty("https://schema.org/identifier")
        private String identifier;
        @JsonProperty("https://schema.org/name")
        private String name;
        @JsonProperty("associatedDisease")
        private List<AssociatedDisease> associatedDisease;
        @JsonProperty("description")
        private String description;
        @JsonProperty("isEncodedByBioChemEntity")
        private String isEncodedByBioChemEntity;
        @JsonProperty("url")
        private String url;
        @JsonProperty("alternateName")
        private String alternateName;
        @JsonProperty("bioChemInteraction")
        private List<Link> bioChemInteraction;
        @JsonProperty("bioChemSimilarity")
        private List<Link> bioChemSimilarity;
        @JsonProperty("hasMolecularFunction")
        private String hasMolecularFunction;
        @JsonProperty("image")
        private String image;
        @JsonProperty("isInvolvedInBiologicalProcess")
        private String isInvolvedInBiologicalProcess;
        @JsonProperty("isPartOfBioChemEntity")
        private IsPartOfBioChemEntity isPartOfBioChemEntity;
        @JsonProperty("mainEntityOfPage")
        private Link mainEntityOfPage;
        @JsonProperty("http://purl.org/pav/retrievedOn")
        private List<DateTimeType> retrievedOn;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<String> getType() {
            return type;
        }

        public void setType(List<String> type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @JsonProperty("https://schema.org/sameAs")
        private List<Link> sameAs;

        public List<Link> getSameAs() {
            return sameAs;
        }

        public void setSameAs(List<Link> sameAs) {
            this.sameAs = sameAs;
        }

        public String getIdentifier() {
            return identifier;
        }

        public void setIdentifier(String identifier) {
            this.identifier = identifier;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getIsEncodedByBioChemEntity() {
            return isEncodedByBioChemEntity;
        }

        public void setIsEncodedByBioChemEntity(String isEncodedByBioChemEntity) {
            this.isEncodedByBioChemEntity = isEncodedByBioChemEntity;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getAlternateName() {
            return alternateName;
        }

        public void setAlternateName(String alternateName) {
            this.alternateName = alternateName;
        }

        public List<Link> getBioChemInteraction() {
            return bioChemInteraction;
        }

        public void setBioChemInteraction(List<Link> bioChemInteraction) {
            this.bioChemInteraction = bioChemInteraction;
        }

        public List<Link> getBioChemSimilarity() {
            return bioChemSimilarity;
        }

        public void setBioChemSimilarity(List<Link> bioChemSimilarity) {
            this.bioChemSimilarity = bioChemSimilarity;
        }

        public String getHasMolecularFunction() {
            return hasMolecularFunction;
        }

        public void setHasMolecularFunction(String hasMolecularFunction) {
            this.hasMolecularFunction = hasMolecularFunction;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getIsInvolvedInBiologicalProcess() {
            return isInvolvedInBiologicalProcess;
        }

        public void setIsInvolvedInBiologicalProcess(String isInvolvedInBiologicalProcess) {
            this.isInvolvedInBiologicalProcess = isInvolvedInBiologicalProcess;
        }

        public List<AssociatedDisease> getAssociatedDisease() {
            return associatedDisease;
        }

        public void setAssociatedDisease(List<AssociatedDisease> associatedDisease) {
            this.associatedDisease = associatedDisease;
        }

        public IsPartOfBioChemEntity getIsPartOfBioChemEntity() {
            return isPartOfBioChemEntity;
        }

        public void setIsPartOfBioChemEntity(IsPartOfBioChemEntity isPartOfBioChemEntity) {
            this.isPartOfBioChemEntity = isPartOfBioChemEntity;
        }

        public Link getMainEntityOfPage() {
            return mainEntityOfPage;
        }

        public void setMainEntityOfPage(Link mainEntityOfPage) {
            this.mainEntityOfPage = mainEntityOfPage;
        }

        public List<DateTimeType> getRetrievedOn() {
            return retrievedOn;
        }

        public void setRetrievedOn(List<DateTimeType> retrievedOn) {
            this.retrievedOn = retrievedOn;
        }
    }

    public static class IsPartOfBioChemEntity {
        @JsonProperty("@type")
        private String type;
        @JsonProperty("url")
        private String url;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class AssociatedDisease {
        @JsonProperty("@type")
        private String type;
        @JsonProperty("name")
        private String name;
        @JsonProperty("code")
        private DeseaseCode code;
        @JsonProperty("id")
        private String id;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public DeseaseCode getCode() {
            return code;
        }

        public void setCode(DeseaseCode code) {
            this.code = code;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public static class DeseaseCode {
        @JsonProperty("@type")
        private String type;
        @JsonProperty("codeValue")
        private String codeValue;
        @JsonProperty("codingSystem")
        private String codingSystem;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCodeValue() {
            return codeValue;
        }

        public void setCodeValue(String codeValue) {
            this.codeValue = codeValue;
        }

        public String getCodingSystem() {
            return codingSystem;
        }

        public void setCodingSystem(String codingSystem) {
            this.codingSystem = codingSystem;
        }
    }

    public static class Link {
        @JsonProperty("@id")
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public static class DateTimeType {
        @JsonProperty("@type")
        private String type;
        @JsonProperty("@value")
        private String value;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Entry> getEntryList() {
        return entryList;
    }

    public void setEntryList(List<Entry> entryList) {
        this.entryList = entryList;
    }
}