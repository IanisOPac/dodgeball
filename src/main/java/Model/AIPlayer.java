package Model;

import java.util.Random;
import Util.Constant;

public class AIPlayer extends Player {

    double xDirection;
    
    public AIPlayer(int _x, int _y, String _side) {
        super(_x, _y, _side);
        updateDestination();
    }

//     public void predictLauchBall(double xProj, double yProj){
//
//     }
    
    // public void predictArrivingBall(){
    //     if(yProj - x){

    //     }
    // }

    public void moveInDirection(){
        if (isHolding()) return;
        if(xDirection > x - 5 && xDirection < x + 5){
            updateDestination();
        }
        if(xDirection < this.x){
            moveLeft();
        }
        else{
            moveRight();
        }
    }

    private void updateDestination() {
        xDirection = new Random().nextDouble() * (Constant.WINDOW_WIDTH - Constant.PLAYER_WIDTH);
    }
}
