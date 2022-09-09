
package ua.nure.order.entity;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Category.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Category">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Fantasy"/>
 *     &lt;enumeration value="Action"/>
 *     &lt;enumeration value="Love novel"/>
 *     &lt;enumeration value="none"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "Category")
@XmlEnum
public enum Category {

    @XmlEnumValue("Fantasy")
    FANTASY("Fantasy"),
    @XmlEnumValue("Action")
    ACTION("Action"),
    @XmlEnumValue("Love novel")
    LOVE_NOVEL("Love novel"),
    @XmlEnumValue("none")
    NONE("none");
    private final String value;

    Category(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Category fromValue(String v) {
        for (Category c: Category.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
