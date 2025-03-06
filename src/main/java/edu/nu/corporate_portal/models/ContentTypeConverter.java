package edu.nu.corporate_portal.models;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * Maps the PostgreSQL enum values ('video', 'photo', 'formatted text', 'table', 'docx', 'xlsx', 'pptx', 'pdf')
 * to our Java ContentType enum (VIDEO, PHOTO, FORMATTED_TEXT, TABLE, DOCX, XLSX, PPTX, PDF).
 */
@Converter(autoApply = true)
public class ContentTypeConverter implements AttributeConverter<ContentType, String> {

    @Override
    public String convertToDatabaseColumn(ContentType attribute) {
        if (attribute == null) return null;
        switch (attribute) {
            case VIDEO:           return "video";
            case PHOTO:           return "photo";
            case FORMATTED_TEXT:  return "formatted text";
            case TABLE:           return "table";
            case DOCX:            return "docx";
            case XLSX:            return "xlsx";
            case PPTX:            return "pptx";
            case PDF:             return "pdf";
            default: throw new IllegalArgumentException("Unknown ContentType: " + attribute);
        }
    }

    @Override
    public ContentType convertToEntityAttribute(String dbValue) {
        if (dbValue == null) return null;
        switch (dbValue) {
            case "video":           return ContentType.VIDEO;
            case "photo":           return ContentType.PHOTO;
            case "formatted text":  return ContentType.FORMATTED_TEXT;
            case "table":           return ContentType.TABLE;
            case "docx":            return ContentType.DOCX;
            case "xlsx":            return ContentType.XLSX;
            case "pptx":            return ContentType.PPTX;
            case "pdf":             return ContentType.PDF;
            default: throw new IllegalArgumentException("Unknown db content type: " + dbValue);
        }
    }
}
