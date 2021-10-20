package eu.dnetlib.rdfconverter.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataciteProteinModel {
    private String id;
    private String doi;
    private Types types;
    List<Creators> creators = new ArrayList<Creators>();
    private String publisher;
    private String publicationYear;
    private static final String schemaVersion = "http://datacite.org/schema/kernel-4";
    List<Identifier> identifiers = new ArrayList<Identifier>();
    List<RelatedIdentifier> relatedIdentifiers = new ArrayList<RelatedIdentifier>();
    List<AlternateIdentifier> alternateIdentifiers = new ArrayList<AlternateIdentifier>();
    List<Description> descriptions = new ArrayList<Description>();
    List<Title> titles = new ArrayList<Title>();
    private List<DataciteDate> dates = new ArrayList<DataciteDate>();

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Types {
        private String resourceType;
        private String resourceTypeGeneral;

        public String getResourceType() {
            return resourceType;
        }

        public void setResourceType(String resourceType) {
            this.resourceType = resourceType;
        }

        public String getResourceTypeGeneral() {
            return resourceTypeGeneral;
        }

        public void setResourceTypeGeneral(String resourceTypeGeneral) {
            this.resourceTypeGeneral = resourceTypeGeneral;
        }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Creators {
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Identifier {
        private String identifier;
        private String identifierType;

        public String getIdentifier() {
            return identifier;
        }

        public void setIdentifier(String identifier) {
            this.identifier = identifier;
        }

        public String getIdentifierType() {
            return identifierType;
        }

        public void setIdentifierType(String identifierType) {
            this.identifierType = identifierType;
        }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class RelatedIdentifier {
        private String relationType;
        private String relatedIdentifier;
        private String relatedIdentifierType;

        public String getRelationType() {
            return relationType;
        }

        public void setRelationType(String relationType) {
            this.relationType = relationType;
        }

        public String getRelatedIdentifier() {
            return relatedIdentifier;
        }

        public void setRelatedIdentifier(String relatedIdentifier) {
            this.relatedIdentifier = relatedIdentifier;
        }

        public String getRelatedIdentifierType() {
            return relatedIdentifierType;
        }

        public void setRelatedIdentifierType(String relatedIdentifierType) {
            this.relatedIdentifierType = relatedIdentifierType;
        }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class AlternateIdentifier {
        private String alternateIdentifier;
        private String alternateIdentifierType;

        public String getAlternateIdentifier() {
            return alternateIdentifier;
        }

        public void setAlternateIdentifier(String alternateIdentifier) {
            this.alternateIdentifier = alternateIdentifier;
        }

        public String getAlternateIdentifierType() {
            return alternateIdentifierType;
        }

        public void setAlternateIdentifierType(String alternateIdentifierType) {
            this.alternateIdentifierType = alternateIdentifierType;
        }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Description {
        private String description;
        private String descriptionType;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDescriptionType() {
            return descriptionType;
        }

        public void setDescriptionType(String descriptionType) {
            this.descriptionType = descriptionType;
        }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Title {
        private String title;
        private String titleType;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitleType() {
            return titleType;
        }

        public void setTitleType(String titleType) {
            this.titleType = titleType;
        }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class DataciteDate {
        private String date;
        private String dateType;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getDateType() {
            return dateType;
        }

        public void setDateType(String dateType) {
            this.dateType = dateType;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public Types getTypes() {
        return types;
    }

    public void setTypes(Types types) {
        this.types = types;
    }

    public List<Creators> getCreators() {
        return creators;
    }

    public void setCreators(List<Creators> creators) {
        this.creators = creators;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }

    public static String getSchemaVersion() {
        return schemaVersion;
    }

    public List<RelatedIdentifier> getRelatedIdentifiers() {
        return relatedIdentifiers;
    }

    public void setRelatedIdentifiers(List<RelatedIdentifier> relatedIdentifiers) {
        this.relatedIdentifiers = relatedIdentifiers;
    }

    public List<AlternateIdentifier> getAlternateIdentifiers() {
        return alternateIdentifiers;
    }

    public void setAlternateIdentifiers(List<AlternateIdentifier> alternateIdentifiers) {
        this.alternateIdentifiers = alternateIdentifiers;
    }

    public List<Description> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<Description> descriptions) {
        this.descriptions = descriptions;
    }

    public List<Title> getTitles() {
        return titles;
    }

    public void setTitles(List<Title> titles) {
        this.titles = titles;
    }

    public List<Identifier> getIdentifiers() {
        return identifiers;
    }

    public void setIdentifiers(List<Identifier> identifiers) {
        this.identifiers = identifiers;
    }

    public List<DataciteDate> getDates() {
        return dates;
    }

    public void setDates(List<DataciteDate> dates) {
        this.dates = dates;
    }
}
