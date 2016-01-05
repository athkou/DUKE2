/*
* Fourth assignment of week 2 from the coursera "Java Programming: Solving Problems with Software" course.
* */

package module2;

import edu.duke.URLResource;

public class URLFinderTestApp
{
    public static void
    main(String argv[])
    {
        //String link = "http://www.dukelearntoprogram.com/course2/data/manylinks.html";
        String link = "http://www.dukelearntoprogram.com/course2/data/newyorktimes.html";

        URLFinder url = new URLFinder(new URLResource(link));
        url.TestURLWithStorage();
    }
}
