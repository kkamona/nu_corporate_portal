package edu.nu.corporate_portal.models;

/**
 * Internal Java representation of possible content types.
 * The actual DB values are handled by ContentTypeConverter.
 */
public enum ContentType {
    VIDEO,
    PHOTO,
    FORMATTED_TEXT,
    TABLE,
    DOCX,
    XLSX,
    PPTX,
    PDF
}
