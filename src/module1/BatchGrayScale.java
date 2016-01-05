package module1;

import java.io.File;
import java.util.List;
import edu.duke.*;

public class BatchGrayScale
{
    public BatchGrayScale(DirectoryResource dir,
                          List<ImageResource> image,
                          List<ImageResource> gray_image)
    {
        dir_        = dir;
        image_      = image;
        gray_image_ = gray_image;
    }

    public void ReadDirectory() { for(File file : dir_.selectedFiles()) image_.add(new ImageResource(file)); }

    public ImageResource MakeGray(ImageResource image_to_convert)
    {
        ImageResource out = new ImageResource(image_to_convert.getWidth(), image_to_convert.getHeight());
        for(Pixel pixel : out.pixels())
        {
            Pixel tmp = image_to_convert.getPixel(pixel.getX(), pixel.getY());
            int average = (tmp.getRed() + tmp.getGreen() + tmp.getBlue()) / 3;

            pixel.setRed(average);
            pixel.setGreen(average);
            pixel.setBlue(average);
        }

        return out;
    }

    public void Convert()
    {
        for(ImageResource img : image_)
        {
            String file_name = img.getFileName();
            file_name = "gray-" + file_name;

            ImageResource gray_img = MakeGray(img);
            gray_img.setFileName(file_name);

            gray_image_.add(gray_img);
        }
    }

    public void Save() { for(ImageResource img : gray_image_) img.save(); }

    private List<ImageResource> image_;
    private List<ImageResource> gray_image_;
    private DirectoryResource dir_;
}
