package module2;

/**
 * Second assignment of week 2 from the coursera "Java Programming: Solving Problems with Software" course.
 */
public class WebLinkTestApp
{
    public static void
    main(String argv[])
    {
        WebLinkFinder web = new WebLinkFinder("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        web.Test();
    }
}
