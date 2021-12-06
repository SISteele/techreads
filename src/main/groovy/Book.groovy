import groovy.sql.Sql
import groovy.json.JsonSlurper

def sql = Sql.newInstance("jdbc:mysql://localhost/techreads", "techreads", "techpass");

sql.execute("delete from book")

def json = new JsonSlurper();

def googleAPI = new URL("https://www.googleapis.com/books/v1/volumes?q=cats");
def response = json.parse(googleAPI);

def book = sql.dataSet("book");
for(int i = 1; i<20; i++) {
    if(response.items[i]) {
        def toSite = response.items[i].volumeInfo;
        book.add(id: i, title: toSite.title, author: toSite.authors.join(", "), rating: toSite.averageRating, cover_art: toSite.imageLinks.thumbnail)
    }
}