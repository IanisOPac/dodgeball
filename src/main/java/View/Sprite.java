package View;

import Util.Constant;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Sprite extends ImageView {
    private final Rectangle2D[] walkClips;
    private final Rectangle2D[] shootClips;
    private Rectangle2D[] dieClips;
    private int numCellsDie;
    private int numCells;
    private int numCellsWalk;
    private int numCellsShoot;
    private final Timeline walkTimeline;
    private final IntegerProperty frameCounter = new SimpleIntegerProperty(0);
    private final Timeline shootTimeline;

    private final Timeline dieTimeline;
    private Timeline timeline;
    public boolean isRunning;

    public Sprite(Image animationImage, int numCells, int numRows, Duration frameTime, String side) {
        this.numCells = numCells;

        double cellWidth  = Constant.PLAYER_WIDTH;//animationImage.getWidth() / numCells; //64x64
        double cellHeight = Constant.PLAYER_HEIGHT;//animationImage.getHeight() / numRows;


        numCellsWalk = 9;

        int lineNumber = 8;
        if(side == "top"){
            lineNumber += 2;
        }

        walkClips = new Rectangle2D[numCellsWalk];
        for (int i = 0; i < numCellsWalk; i++) {
            walkClips[i] = new Rectangle2D(
                    i * cellWidth, cellHeight*lineNumber,
                    cellWidth, cellHeight
            );
        }

        setImage(animationImage);
        setViewport(walkClips[0]);

        walkTimeline = new Timeline(
                new KeyFrame(frameTime, event -> {
                    frameCounter.set((frameCounter.get() + 1) % numCellsWalk);
                    setViewport(walkClips[frameCounter.get()]);
                })
        );

        numCellsShoot = 13;
        lineNumber += 8;

        shootClips = new Rectangle2D[numCellsShoot];
        for (int i = 0; i < numCellsShoot; i++){
            shootClips[i] = new Rectangle2D(
                    i * cellWidth, cellHeight*lineNumber,
                    cellWidth, cellHeight
            );
        }

        shootTimeline = new Timeline(
                new KeyFrame(frameTime, event -> {
                    frameCounter.set((frameCounter.get() + 1) % numCellsShoot);
                    setViewport(shootClips[frameCounter.get()]);
                }));

        numCellsDie = 6;
        lineNumber = 20;

        dieClips = new Rectangle2D[numCellsDie];
        for (int i = 0; i < numCellsDie; i++){
            dieClips[i] = new Rectangle2D(
                    i * cellWidth, cellHeight*lineNumber,
                    cellWidth, cellHeight
            );
        }

        dieTimeline = new Timeline(
                new KeyFrame(frameTime, event -> {
                    frameCounter.set((frameCounter.get() + 1) % numCellsDie);
                    setViewport(dieClips[frameCounter.get()]);
                }));

        timeline = walkTimeline;
        isRunning = false;
    }

    public void playContinuously() {
        isRunning = true;
        frameCounter.set(0);
        timeline = walkTimeline;
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.stop();
        timeline.playFromStart();
    }

    public void playShoot(){
        frameCounter.set(0);
        timeline.stop();
        timeline = shootTimeline;
        timeline.setCycleCount(numCellsShoot);
        timeline.setOnFinished(e -> playContinuously());
        timeline.playFromStart();
    }

    public void playDie(){
        frameCounter.set(0);
        timeline.stop();
        timeline = dieTimeline;
        timeline.setCycleCount(numCellsDie);
        timeline.setOnFinished(e -> stop());
        timeline.playFromStart();
    }

    public void stop() {
        setViewport(dieClips[5]);
        dieTimeline.stop();
    }
}
