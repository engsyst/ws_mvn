//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.05.23 at 06:10:04 PM EEST 
//


package shop.entity;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for Book complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Book">
 *   &lt;complexContent>
 *     &lt;extension base="{http://entity.shop}Product">
 *       &lt;sequence>
 *         &lt;element name="author" type="{http://www.w3.org/2001/XMLSchema}token" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="isbn" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;pattern value="ISBN-[0-9]{5,5}-[0-9]{4,4}"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Book", propOrder = {
    "author",
    "isbn"
})
public class Book
    extends Product
{

    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected List<String> author;
    protected String isbn;

    public Book() {
		super();
	}

    public Book(Long id, String title, double price, List<String> categories, String description, List<String> author,
			String isbn) {
		super(id, title, price, categories, description);
		this.author = author;
		this.isbn = isbn;
	}

    /**
     * Gets the value of the author property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the author property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAuthor().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getAuthor() {
        if (author == null) {
            author = new ArrayList<>();
        }
        return this.author;
    }

    /**
     * Gets the value of the isbn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Sets the value of the isbn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsbn(String value) {
        this.isbn = value;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Book [id=");
		builder.append(id);
		builder.append(", title=");
		builder.append(title);
		builder.append(", price=");
		builder.append(price);
		builder.append(", categories=");
		builder.append(categories);
		builder.append(", description=");
		builder.append(description);
		builder.append(", author=");
		builder.append(author);
		builder.append(", isbn=");
		builder.append(isbn);
		builder.append("]");
		return builder.toString();
	}

}
