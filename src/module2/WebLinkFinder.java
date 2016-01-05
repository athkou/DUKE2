/*
* Second assignment of week 2 from the coursera "Java Programming: Solving Problems with Software" course.
*/

package module2;

import edu.duke.*;

public class WebLinkFinder
{
    public WebLinkFinder(String url) { url_ = url; }

    public void Test()
    {
        URLResource url = new URLResource(url_);
        int count = 0;
        for(String word : url.words())
        {
            String tmp = word.toLowerCase();
            if(tmp.contains("youtube.com"))
            {
                int begin = word.indexOf("\"");
                int end  = word.indexOf("\"", begin + 1);

                tmp = word.substring(begin + 1, end);
                System.out.println("Whole word: " + word);
                System.out.println("Only the link: " + tmp);
                System.out.println();

                ++count;
            }
        }
        System.out.println("The webpage contained " + count + " links to youtube videos");
    }

    private String url_;
}
