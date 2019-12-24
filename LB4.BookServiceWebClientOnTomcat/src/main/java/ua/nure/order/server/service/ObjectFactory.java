
package ua.nure.order.server.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ua.nure.order.server.service package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _FindByAuthorResponse_QNAME = new QName("http://order.nure.ua/server/service", "findByAuthorResponse");
    private final static QName _DAOException_QNAME = new QName("http://order.nure.ua/server/service", "DAOException");
    private final static QName _AddBook_QNAME = new QName("http://order.nure.ua/server/service", "addBook");
    private final static QName _DeleteBookResponse_QNAME = new QName("http://order.nure.ua/server/service", "deleteBookResponse");
    private final static QName _FindByTitle_QNAME = new QName("http://order.nure.ua/server/service", "findByTitle");
    private final static QName _FindByAuthor_QNAME = new QName("http://order.nure.ua/server/service", "findByAuthor");
    private final static QName _FindByTitleResponse_QNAME = new QName("http://order.nure.ua/server/service", "findByTitleResponse");
    private final static QName _ListBooksResponse_QNAME = new QName("http://order.nure.ua/server/service", "listBooksResponse");
    private final static QName _GetBook_QNAME = new QName("http://order.nure.ua/server/service", "getBook");
    private final static QName _AddBookResponse_QNAME = new QName("http://order.nure.ua/server/service", "addBookResponse");
    private final static QName _ListBooks_QNAME = new QName("http://order.nure.ua/server/service", "listBooks");
    private final static QName _DeleteBook_QNAME = new QName("http://order.nure.ua/server/service", "deleteBook");
    private final static QName _GetBookResponse_QNAME = new QName("http://order.nure.ua/server/service", "getBookResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ua.nure.order.server.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetBookResponse }
     * 
     */
    public GetBookResponse createGetBookResponse() {
        return new GetBookResponse();
    }

    /**
     * Create an instance of {@link DeleteBook }
     * 
     */
    public DeleteBook createDeleteBook() {
        return new DeleteBook();
    }

    /**
     * Create an instance of {@link ListBooks }
     * 
     */
    public ListBooks createListBooks() {
        return new ListBooks();
    }

    /**
     * Create an instance of {@link FindByAuthor }
     * 
     */
    public FindByAuthor createFindByAuthor() {
        return new FindByAuthor();
    }

    /**
     * Create an instance of {@link FindByTitleResponse }
     * 
     */
    public FindByTitleResponse createFindByTitleResponse() {
        return new FindByTitleResponse();
    }

    /**
     * Create an instance of {@link AddBookResponse }
     * 
     */
    public AddBookResponse createAddBookResponse() {
        return new AddBookResponse();
    }

    /**
     * Create an instance of {@link ListBooksResponse }
     * 
     */
    public ListBooksResponse createListBooksResponse() {
        return new ListBooksResponse();
    }

    /**
     * Create an instance of {@link GetBook }
     * 
     */
    public GetBook createGetBook() {
        return new GetBook();
    }

    /**
     * Create an instance of {@link DAOException }
     * 
     */
    public DAOException createDAOException() {
        return new DAOException();
    }

    /**
     * Create an instance of {@link FindByAuthorResponse }
     * 
     */
    public FindByAuthorResponse createFindByAuthorResponse() {
        return new FindByAuthorResponse();
    }

    /**
     * Create an instance of {@link DeleteBookResponse }
     * 
     */
    public DeleteBookResponse createDeleteBookResponse() {
        return new DeleteBookResponse();
    }

    /**
     * Create an instance of {@link FindByTitle }
     * 
     */
    public FindByTitle createFindByTitle() {
        return new FindByTitle();
    }

    /**
     * Create an instance of {@link AddBook }
     * 
     */
    public AddBook createAddBook() {
        return new AddBook();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindByAuthorResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://order.nure.ua/server/service", name = "findByAuthorResponse")
    public JAXBElement<FindByAuthorResponse> createFindByAuthorResponse(FindByAuthorResponse value) {
        return new JAXBElement<FindByAuthorResponse>(_FindByAuthorResponse_QNAME, FindByAuthorResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DAOException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://order.nure.ua/server/service", name = "DAOException")
    public JAXBElement<DAOException> createDAOException(DAOException value) {
        return new JAXBElement<DAOException>(_DAOException_QNAME, DAOException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddBook }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://order.nure.ua/server/service", name = "addBook")
    public JAXBElement<AddBook> createAddBook(AddBook value) {
        return new JAXBElement<AddBook>(_AddBook_QNAME, AddBook.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteBookResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://order.nure.ua/server/service", name = "deleteBookResponse")
    public JAXBElement<DeleteBookResponse> createDeleteBookResponse(DeleteBookResponse value) {
        return new JAXBElement<DeleteBookResponse>(_DeleteBookResponse_QNAME, DeleteBookResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindByTitle }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://order.nure.ua/server/service", name = "findByTitle")
    public JAXBElement<FindByTitle> createFindByTitle(FindByTitle value) {
        return new JAXBElement<FindByTitle>(_FindByTitle_QNAME, FindByTitle.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindByAuthor }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://order.nure.ua/server/service", name = "findByAuthor")
    public JAXBElement<FindByAuthor> createFindByAuthor(FindByAuthor value) {
        return new JAXBElement<FindByAuthor>(_FindByAuthor_QNAME, FindByAuthor.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindByTitleResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://order.nure.ua/server/service", name = "findByTitleResponse")
    public JAXBElement<FindByTitleResponse> createFindByTitleResponse(FindByTitleResponse value) {
        return new JAXBElement<FindByTitleResponse>(_FindByTitleResponse_QNAME, FindByTitleResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListBooksResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://order.nure.ua/server/service", name = "listBooksResponse")
    public JAXBElement<ListBooksResponse> createListBooksResponse(ListBooksResponse value) {
        return new JAXBElement<ListBooksResponse>(_ListBooksResponse_QNAME, ListBooksResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBook }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://order.nure.ua/server/service", name = "getBook")
    public JAXBElement<GetBook> createGetBook(GetBook value) {
        return new JAXBElement<GetBook>(_GetBook_QNAME, GetBook.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddBookResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://order.nure.ua/server/service", name = "addBookResponse")
    public JAXBElement<AddBookResponse> createAddBookResponse(AddBookResponse value) {
        return new JAXBElement<AddBookResponse>(_AddBookResponse_QNAME, AddBookResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListBooks }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://order.nure.ua/server/service", name = "listBooks")
    public JAXBElement<ListBooks> createListBooks(ListBooks value) {
        return new JAXBElement<ListBooks>(_ListBooks_QNAME, ListBooks.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteBook }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://order.nure.ua/server/service", name = "deleteBook")
    public JAXBElement<DeleteBook> createDeleteBook(DeleteBook value) {
        return new JAXBElement<DeleteBook>(_DeleteBook_QNAME, DeleteBook.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBookResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://order.nure.ua/server/service", name = "getBookResponse")
    public JAXBElement<GetBookResponse> createGetBookResponse(GetBookResponse value) {
        return new JAXBElement<GetBookResponse>(_GetBookResponse_QNAME, GetBookResponse.class, null, value);
    }

}
