/*
* Fourth assignment of week 2 from the coursera "Java Programming: Solving Problems with Software" course.
* */

package module2;

import edu.duke.StorageResource;
import edu.duke.URLResource;

public class URLFinder
{
    public URLFinder(URLResource url) { url_pool_ = FindURLs(url.asString()); }

    public StorageResource FindURLs(String url)
    {
        StorageResource store = new StorageResource();

        int start = 0;

        while (true)
        {
            int index = url.indexOf("href=", start);
            if(index == -1) break;

            int firstQuote = index+6; // after href="
            int endQuote = url.indexOf("\"", firstQuote);
            String sub = url.substring(firstQuote, endQuote);

            if (sub.startsWith("http")) store.add(sub);
            start = endQuote + 1;
        }
        return store;
    }

    public void TestURLWithStorage()
    {
        int https_count         = 0;
        int com_count           = 0;
        int ends_with_com_count = 0;
        int dot_count           = 0;

        for(String link : url_pool_.data())
        {
            if(link.startsWith("https"))                        ++https_count;
            if(link.contains(".com"))                           ++com_count;
            if(link.endsWith(".com") || link.endsWith(".com/")) ++ends_with_com_count;
            int start = 0;
            while (true)
            {
                int location = link.indexOf(".", start);
                if (location == -1) break;
                else
                {
                    ++dot_count;
                    start = location + 1;
                }
            }
            System.out.println(link);
        }

        System.out.println("\nNumber of URL of this page: "                       + url_pool_.size());
        System.out.println("Number of URL that start with \"https\": "            + https_count);
        System.out.println("Number of links that have \".com\" in the link: "     + com_count);
        System.out.println("Number of links that end with \".com\" in the link: " + ends_with_com_count);
        System.out.println("Total number of \".\" that appear in all the links: " + dot_count);
    }

    private StorageResource url_pool_;
}
