package com.KUAlchemists.ui.controllers;

import com.KUAlchemists.backend.handlers.BoardHandler;
import com.KUAlchemists.backend.handlers.ScoringHandler;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class FinalScoringController {

    @FXML
    public Text drawMessage;
    @FXML
    private Text additional1;

    @FXML
    private Text additional2;

    @FXML
    private Text additional3;

    @FXML
    private Text additional4;

    @FXML
    private Text alchemistName1;

    @FXML
    private Text alchemistName2;

    @FXML
    private Text alchemistName3;

    @FXML
    private Text alchemistName4;

    @FXML
    private Text finalPoint1;

    @FXML
    private Text finalPoint2;

    @FXML
    private Text finalPoint3;

    @FXML
    private Text finalPoint4;

    @FXML
    private ImageView userImagePane1;

    @FXML
    private ImageView userImagePane2;

    @FXML
    private ImageView userImagePane3;

    @FXML
    private ImageView userImagePane4;

    @FXML
    private Pane userMainSlot1;

    @FXML
    private Pane userMainSlot2;

    @FXML
    private Pane userMainSlot3;

    @FXML
    private Pane userMainSlot4;

    private UserSlot[] userSlots;

    public void initialize(){
        drawMessage.setVisible(false);
        String userName = "";
        Image image1 = null;
        ArrayList<Integer> rankingList = ScoringHandler.getInstance().handleGetRankingList(); // get the ranking of the players
        // we have a ranking list of the players like [2, 1, 3]
        // it means that the player 2 is the winner, player 1 is the second and player 3 is the third
        // we can use this ranking list to get the player list in the order of the ranking
        // we need to create another list that contains the player list in the order of the ranking
        ArrayList<String> playerPosition = new ArrayList<>();
        for(int i = 0; i < rankingList.size(); i++){
            int playerIndex = rankingList.get(i);
            String player = "player" + (playerIndex + 1); // player1, player2, player3, player4
            playerPosition.add(player);
        }

        UserSlot user1 = new UserSlot(userMainSlot1, userImagePane1, alchemistName1, finalPoint1, additional1);
        UserSlot user2 = new UserSlot(userMainSlot2, userImagePane2, alchemistName2, finalPoint2, additional2);
        UserSlot user3 = new UserSlot(userMainSlot3, userImagePane3, alchemistName3, finalPoint3, additional3);
        UserSlot user4 = new UserSlot(userMainSlot4, userImagePane4, alchemistName4, finalPoint4, additional4);

        userSlots = new UserSlot[]{user1, user2, user3, user4};
        for(UserSlot userSlot: userSlots){
            userSlot.getUserMainSlot().setVisible(false);
        }
        for(int i=0; i < playerPosition.size(); i++){

            int score = ScoringHandler.getInstance().handleGetScores(rankingList.get(i));
            userSlots[i].getFinalPoint().setText("Final Point: " + String.valueOf(score));
            userSlots[i].getAlchemistName().setText("Alchemist Name: " + playerPosition.get(i));
            userSlots[i].getUserMainSlot().setVisible(true);
            String avatar = BoardHandler.getInstance().getAvatarPath(rankingList.get(i));
            String imagePath = "/com.KUAlchemists/images/profile_p/" + avatar + ".png";
            // Load the image using the class loader to ensure it works regardless of the build type
            try {
                Image image = new Image(getClass().getResourceAsStream(imagePath));
                userSlots[i].setUserAvatarImage(image);
            }catch (Exception e){
                System.err.println(e.getMessage());
            }
        }
        int draw = ScoringHandler.getInstance().handleDraw();
        if (draw == 1){
            drawMessage.setText("There is a draw!");
            drawMessage.setVisible(true);
        }

    }


    private class UserSlot{
        private String userName;
        private Pane userMainSlot;
        private Image userAvatarImage;
        private ImageView userAvatarImageView;
        private Text alchemistName;
        private Text finalPoint;
        private Text additional;

        public UserSlot(Pane userMainSlot, ImageView userAvatarImageView, Text alchemistName, Text finalPoint, Text additional) {
            this.userMainSlot = userMainSlot;
            this.userAvatarImageView = userAvatarImageView;
            this.alchemistName = alchemistName;
            this.finalPoint = finalPoint;
            this.additional = additional;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public Pane getUserMainSlot() {
            return userMainSlot;
        }

        public void setUserMainSlot(Pane userMainSlot) {
            this.userMainSlot = userMainSlot;
        }

        public Image getUserAvatarImage() {
            return userAvatarImage;
        }

        public void setUserAvatarImage(Image userAvatarImage) {
            this.userAvatarImage = userAvatarImage;
            setUserAvatarImageView(this.userAvatarImage);
        }

        public ImageView getUserAvatarImageView() {
            return userAvatarImageView;
        }

        public void setUserAvatarImageView(Image userAvatarImageView) {
            this.userAvatarImageView.setImage(userAvatarImageView);
        }

        public Text getAlchemistName() {
            return alchemistName;
        }

        public void setAlchemistName(Text alchemistName) {
            this.alchemistName = alchemistName;
        }

        public Text getFinalPoint() {
            return finalPoint;
        }

        public void setFinalPoint(Text finalPoint) {
            this.finalPoint = finalPoint;
        }

        public Text getAdditional() {
            return additional;
        }

        public void setAdditional(Text additional) {
            this.additional = additional;
        }
    }
}
