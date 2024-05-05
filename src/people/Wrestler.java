package people;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import people.Person;

import java.util.Random;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Wrestler extends Person {
    private double height;
    private int weight;
    // SKILLS
    private int stanceScore;
    // While in the neutral (standing) position, some wrestlers prefer a square stance while others prefer a staggered stance.
    // Either stance can work equally well. Make sure you have most of your weight on the balls of your feet as opposed to your heels.
    // You should never be standing straight up and leaving yourself open to a takedown from your opponent.
    // You should utilize a comfortable but effective stance. You need a stance that allows you to attack as well as defend.
    private int motionScore;
    // Wrestling involves a tremendous amount of motion. You may circle your opponent. You may attack your opponent with a takedown attempt.
    // You may snap your opponent to the mat and spin around behind him. You may sprawl to defend against a takedown.
    // You need to learn to move fluidly and quickly. You don't want to be clumsy or sloppy.
    // You need to execute moves explosively and crisply.
    private int levelChangeScore;
    // Before a wrestler shoots a takedown, he must lower his level. This is accomplished by lowering his hips.
    // When a wrestler sprawls to counter a takedown he is also using level change.
    // In addition, a wrestler may lower and raise his level to force a reaction from his opponent.
    // A wrestler needs to be able to maintain good posture while changing levels.
    // For instance, you don't want to get overextended when shooting a takedown because you forgot to lower your level first.
    // Always bend at the knees, not at the waist.
    private int penetrationScore;
    // Obviously, when you shoot for a takedown on your opponent, you don't merely reach for his legs or dive wildly for his legs.
    // Instead, you make sure you are close enough to your opponent and then take a deep penetration step keeping your back straight and your head up.
    // You lower your level and penetrate deeply being sure to keep your hips forward and beneath your shoulders while you drive through your opponent.
    private int liftingScore;
    // A wrestler often lifts his opponent off the mat when executing a takedown.
    // He may also lift an opponent who has performed a stand up from the bottom position.
    // He lifts his opponent and returns him to the mat. The legs and hips are most important when lifting.
    // For instance, when you shoot a double leg, you don't lift with your back.
    // You get your hips squarely beneath you and then use your leg and hip strength to lift your opponent off the mat.
    // When a wrestler has been lifted off the mat, he is in a very vulnerable position lacking support and balance.
    private int backStepScore;
    // A back step is most often seen when a wrestler executes a throw.
    private int backArchScore;
    // When a wrestler attempts a throw, he often pops his hips under his opponent while arching his back.
    private int overAllScore;
    private int numOfFightsWon = 0;

    public Wrestler() {

    }

    public Wrestler(int age, String name, String school) {
        super(age, name, school);
        Random random = new Random();
        int highWeight = 400;
        int lowWeight = 200;
        this.weight = random.nextInt(highWeight - lowWeight) + lowWeight;
        this.height = (random.nextDouble() + 5.5);
    }

    public int getNumOfFightsWon() {
        return numOfFightsWon;
    }
    public void addAWin() {
        numOfFightsWon++;
    }

    public int getOverAllScore() {
        setOverAllScore();
        return overAllScore;
    }
    private void setOverAllScore() {
        overAllScore = weight + stanceScore + motionScore + levelChangeScore + penetrationScore + liftingScore + backStepScore + backArchScore;
    }

    public int getStanceScore() {
        return stanceScore;
    }
    public void setStanceScore(int stanceScore) {
        this.stanceScore = stanceScore;
    }
    public void changeStanceScore(int howMuch) {
        stanceScore += howMuch;
    }

    public int getMotionScore() {
        return motionScore;
    }
    public void setMotionScore(int motionScore) {
        this.motionScore = motionScore;
    }
    public void changeMotionScore(int howMuch) {
        motionScore += howMuch;
    }

    public int getLevelChangeScore() {
        return levelChangeScore;
    }
    public void setLevelChangeScore(int levelChangeScore) {
        this.levelChangeScore = levelChangeScore;
    }
    public void changeLevelChangeScore(int howMuch) {
        levelChangeScore += howMuch;
    }

    public int getPenetrationScore() {
        return penetrationScore;
    }
    public void setPenetrationScore(int penetrationScore) {
        this.penetrationScore = penetrationScore;
    }
    public void changePenetrationScore(int howMuch) {
        penetrationScore += howMuch;
    }

    public int getLiftingScore() {
        return liftingScore;
    }
    public void setLiftingScore(int liftingScore) {
        this.liftingScore = liftingScore;
    }
    public void changeLiftingScore(int howMuch) {
        liftingScore += howMuch;
    }

    public int getBackStepScore() {
        return backStepScore;
    }
    public void setBackStepScore(int backStepScore) {
        this.backStepScore = backStepScore;
    }
    public void changeBackStepScore(int howMuch) {
        backStepScore += howMuch;
    }

    public int getBackArchScore() {
        return backArchScore;
    }
    public void setBackArchScore(int backArchScore) {
        this.backArchScore = backArchScore;
    }
    public void changeBackArchScore(int howMuch) {
        backArchScore += howMuch;
    }

    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    public void changeWeight(int howMuch) {
        weight += howMuch;
    }

    public double getHeight() {
        return height;
    }

}
