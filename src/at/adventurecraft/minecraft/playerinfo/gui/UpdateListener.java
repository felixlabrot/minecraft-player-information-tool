package at.adventurecraft.minecraft.playerinfo.gui;

import static at.adventurecraft.minecraft.playerinfo.MinecraftPlayerInfo.getAllNames;
import static at.adventurecraft.minecraft.playerinfo.MinecraftPlayerInfo.getCurrentName;
import static at.adventurecraft.minecraft.playerinfo.MinecraftPlayerInfo.getDemo;
import static at.adventurecraft.minecraft.playerinfo.MinecraftPlayerInfo.getInfoJSON;
import static at.adventurecraft.minecraft.playerinfo.MinecraftPlayerInfo.getLegacy;
import static at.adventurecraft.minecraft.playerinfo.MinecraftPlayerInfo.getUUID;
import at.adventurecraft.minecraft.playerinfo.NoSuchPlayerException;
import static at.adventurecraft.minecraft.playerinfo.gui.PlayerInfoWindow.DEFAULT_IMAGE;
import at.adventurecraft.minecraft.playerinfo.util.RuntimeIOException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import org.json.JSONObject;

final class UpdateListener extends KeyAdapter implements ActionListener {
    private final PlayerInfoWindow piw;
    
    UpdateListener(PlayerInfoWindow piw) {
        this.piw = piw;
    }
    
    @Override public void keyPressed(KeyEvent ev) {
        if(ev.getKeyCode() == KeyEvent.VK_ENTER)
            exec();
    }
    
    @Override public void actionPerformed(ActionEvent ev) {
        exec();
    }
    
    private void exec() {
        String input = piw.currentName.getText().trim();
        String uuid;
        JSONObject info_json;
        
        try {
            uuid = getUUID(input);
            
            piw.currentName.setText(getCurrentName(input));
            
            piw.uuid.setText(uuid);
            
            if(getLegacy(input)) piw.legacy.setText("Minecraft-Account");
            else piw.legacy.setText("Mojang-Account");
            
            if(getDemo(input)) piw.demo.setText("Demo Account");
            else piw.demo.setText("Paid Account");
            
            Map<String, Long> allNamesMap = getAllNames(uuid);
            piw.allNamesModel.clear();
            
            allNamesMap.keySet().stream().forEach((name) -> {
                long timestamp = allNamesMap.get(name);
                
                if(timestamp == 0) {
                    piw.allNamesModel.addElement(name);
                } else {
                    Date d = new Date(timestamp);
                    piw.allNamesModel.addElement(name + " (" + d + ")");
                }
            });
            
            info_json = getInfoJSON(uuid);
            piw.details.setText(info_json.toString());
            
            piw.requestTime.setText(getLastSkinChangeTime(info_json).toString());
        } catch (NoSuchPlayerException ex) {
            clear();
            JOptionPane.showMessageDialog(piw, "A player with this name doesn't exist!", "Player doesn't exist", JOptionPane.WARNING_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(piw, "Can't retrive user information.\n" + ex.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        try {
            URL url = new URL("http://www.minecraft-skin-viewer.net/3d.php?layers=true&aa=true&a=0&w=330&wt=10&abg=330&abd=40&ajg=340&ajd=20&ratio=9&format=png&login=" + piw.currentName.getText() + "&headOnly=false&displayHairs=true&randomness=358");
            Image skin = ImageIO.read(url);
            piw.skin.setImage(skin);
            piw.skin.setAlpha(1);
        } catch (IOException ex) {
            try {
                piw.skin.setAlpha(0);
                piw.skin.setImage(DEFAULT_IMAGE);
            } catch(IOException e) {
                throw new RuntimeIOException(e);
            }
        }
    }
    /*
    private static URL getSkinUrl(JSONObject info) throws IOException {
        if(info == null) throw new IOException();
        try {
            return new URL(info.getJSONArray("properties").getJSONObject(0).getJSONObject("value").getJSONObject("textures").getJSONObject("SKIN").getString("url"));
        } catch(JSONException e) {
            return DEFAULT_IMAGE;
        }
    }
    */
    private static Date getLastSkinChangeTime(JSONObject info) throws IOException {
        if(info == null) throw new IOException();
        return new Date(info.getJSONArray("properties").getJSONObject(0).getJSONObject("value").getLong("timestamp"));
    }
    
    private void clear() {
        piw.uuid.setText(null);
        piw.legacy.setText(null);
        piw.demo.setText(null);
        piw.allNamesModel.clear();
        piw.details.setText(null);
        piw.requestTime.setText("- Dump Time -");
        
        try {
            piw.skin.setAlpha(0);
            piw.skin.setImage(DEFAULT_IMAGE);
        } catch(IOException e) {
            throw new RuntimeIOException(e);
        }
    }
}
