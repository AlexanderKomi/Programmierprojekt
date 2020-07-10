package common.actor;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.*;

/**
 * Drawable is an image with bounds checking.
 * Also support Sprites, scaling and rotation.
 * <p>
 * Use the constructors as you like :)
 * Its easier to support multiple projects, when many constructors are given as an interface.
 * <p>
 * Every Drawable can be drawn on a canvas.
 */
public class Drawable extends Observable {

    private static int id_counter = 0;
    public final   int id;

    private double x;
    private double y;
    private double height;
    private double width;

    private String name;

    private int    switchingBuffer = 0;
    private double switchingDelay  = 0;

    private double scaleX = 1.0;
    private double scaleY = 1.0;

    private final ImageView        imageView       = new ImageView();
    private final ArrayList<Image> switchingImages = new ArrayList<>();


    protected Drawable(final String pictureFileName) {
        this(pictureFileName, 0, 0);
    }

    protected Drawable(final String pictureFileName, final double x, final double y) {
        this.setCurrentImage(loadPicture(pictureFileName));
        if (this.getCurrentImage() == null) {
            throw new NullPointerException("Current Image is null! Path to image: " + pictureFileName);
        }

        this.x = x;
        this.y = y;
        this.id = id_counter;
        id_counter++;
    }

    protected Drawable(final List<String> pictureFilePaths, final double x, final double y, final int delay) {
        this(x, y, delay);
        for (String filePath : pictureFilePaths) {
            this.switchingImages.add(loadPicture(filePath));
        }
        if (this.switchingImages.size() > 0) {
            this.setCurrentImage(this.switchingImages.get(0));
        }
    }

    protected Drawable(final String mustHave, final List<String> asList, final double x, final double y, final int delay) {
        this(asList, x, y, delay);
        this.getSwitchingImages().add(this.loadPicture(mustHave));
    }

    private Drawable(final double x, final double y, final int delay) {
        this.x = x;
        this.y = y;
        this.switchingDelay = delay;
        this.id = id_counter;
        id_counter++;
    }

    protected Drawable(final double x, final double y, final int delay, final String[] pictureFileName) {
        this(Arrays.asList(pictureFileName), x, y, delay);
    }

    protected Drawable(final double x, final double y, final double scale, final String picturePath) {
        this(picturePath, x, y);
        this.scaleImage(scale);
    }

    private Image loadPicture(final String fileName) throws NullPointerException {
        if (fileName == null) {
            throw new NullPointerException("Relative path to an image can not be null.");
        }
        this.name = fileName;
        return TextureBuffer.loadImage(fileName);
    }

    /**
     * Switch switchingImages based on buffer implementation.
     */
    private void switchImages() {
        if (this.switchingImages.isEmpty()) {
            return;
        }
        if (this.switchingBuffer < this.switchingDelay) {
            this.switchingBuffer++;
            return;
        }
        switchToNextImage();
    }

    private void switchToNextImage() {
        this.switchingBuffer = 0;
        final int index = this.switchingImages.indexOf(this.getCurrentImage());
        if (index < this.switchingImages.size() - 1) {
            this.setCurrentImage(this.switchingImages.get(index + 1));
        } else {
            this.setCurrentImage(this.switchingImages.get(0));
        }
    }

    protected void scaleImageWidth(final double factor) {
        if (!(factor > 0)) {
            this.x += this.width;
        }
        this.width *= factor;
        this.scaleX *= factor;
        this.imageView.setFitWidth(scaleX);
        this.imageView.setScaleX(scaleX);
    }

    protected void scaleImageHeight(final double factor) {
        this.height *= factor;
        this.scaleY *= factor;
        this.imageView.setFitHeight(scaleY);
        this.imageView.setScaleY(scaleY);
    }

    protected void scaleImage(final double factor) {
        scaleImageHeight(factor);
        scaleImageWidth(factor);
    }

    // ---------------------------------- START DRAW ----------------------------------

    public void draw(Canvas canvas) {
        draw(canvas, 0, 0);
    }

    public void draw(Canvas canvas, final double offset_to_new_x, final double offset_to_new_y) {
        draw(canvas, canvas.getWidth(), canvas.getHeight(), offset_to_new_x, offset_to_new_y);
    }

    public void draw(
            Canvas canvas,
            final double canvas_width,
            final double canvas_height,
            final double offset_to_new_x,
            final double offset_to_new_y) {

        final boolean[] isInBounds = CollisionCheck.isInBounds(
                this.x,
                this.y,
                this.width,
                this.height,
                canvas_width,
                canvas_height,
                offset_to_new_x,
                offset_to_new_y);
        final double[] in_bounds_pos = calcPosAfterBounds(isInBounds, offset_to_new_x, offset_to_new_y);
        final double[] old_pos       = this.getPos();
        this.setPos(in_bounds_pos);
        this.setPos(beforeDrawing(old_pos, in_bounds_pos)); // Maybe reset ? :)
        switchImages();
        //this.setCurrentImage(temp);
        canvas.getGraphicsContext2D().drawImage(this.getCurrentImage(), this.x, this.y, this.width, this.height);
    }

    /**
     * Override this method, to apply any new checks or manipulate the position before the new position is drawn.
     *
     * @param current_pos The current position of the Drawable
     * @param new_pos     The next position of the Drawable
     * @return Returns the new position of the Drawable.
     */
    protected double[] beforeDrawing(double[] current_pos, double[] new_pos) {
        return new_pos;
    }

    // ---------------------------------- END DRAW ----------------------------------

    private double[] calcPosAfterBounds(boolean[] isInBounds, double new_x, double new_y) {
        final double[] temp = new double[]{this.x, this.y};
        if (isInBounds[0]) { temp[0] += (new_x); }
        if (isInBounds[1]) { temp[1] += (new_y); }
        return temp;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Drawable) {
            final Drawable d = (Drawable) obj;
            return Arrays.equals(this.getPos(), d.getPos()) &&
                   this.height == d.height &&
                   this.width == d.width &&
                   this.name.equals(d.name) &&
                   this.getCurrentImage().equals(d.getCurrentImage());
        }
        return false;
    }

    @Override
    public String toString() {
        return this.getClass() + "(" + "name:" +
               this.name + ", " +
               "x:" + this.x + ", " +
               "y:" + this.y + ", " +
               "width:" + this.width + ", " +
               "height:" + this.height + ")";
    }

    protected final double getScaleX() {
        return this.scaleX;
    }

    public void setPos(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public void setPos(final double[] pos) {
        this.x = pos[0];
        this.y = pos[1];
    }

    public double[] getPos() {
        return new double[]{this.x, this.y};
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setSize(final double size) {
        this.width  = size; this.height = size;
    }

    public String getName() {
        return name;
    }

    protected void setSwitchingDelay(final double switchingDelay) {
        this.switchingDelay = switchingDelay;
    }

    protected Image getCurrentImage() {
        return this.imageView.getImage();
    }

    public void setCurrentImage(Image currentImage) {
        this.imageView.setImage(currentImage);
        this.height = this.getCurrentImage().getHeight();
        this.width = this.getCurrentImage().getWidth();
    }

    public void setCurrentImage(final String filePath) {
        setCurrentImage(loadPicture(filePath));
    }

    private ArrayList<Image> getSwitchingImages() {
        return switchingImages;
    }

}
