/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean.theme;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author abdoulaye
 */
@ManagedBean(name = "themes")
@SessionScoped
public class themes {

    List<String> themes;

    @PostConstruct
    public void init() {
        themes = new ArrayList<>();
        themes.add("afterdark");
        themes.add("afternoon");
        themes.add("afterwork");
        themes.add("aristo");
        themes.add("black-tie");
        themes.add("blitzer");
        themes.add("bluesky");
        themes.add("bootstrap");
        themes.add("casablanca");
        themes.add("cupertino");
        themes.add("cruze");
        themes.add("dark-hive");
        themes.add("delta");
        themes.add("dot-luv");
        themes.add("eggplant");
        themes.add("excite-bike");
        themes.add("flick");
        themes.add("glass-x");
        themes.add("home");
        themes.add("hot-sneaks");
        themes.add("humanity");
        themes.add("le-frog");
        themes.add("midnight");
        themes.add("mint-choc");
        themes.add("overcast");
        themes.add("pepper-grinder");
        themes.add("rocket");
        themes.add("sam");
        themes.add("smoothness");
        themes.add("south-street");
        themes.add("start");
        themes.add("sunny");
        themes.add("swanky-purse");
        themes.add("trontastic");
        themes.add("ui-darkness");
        themes.add("ui-lightness");
        themes.add("vader");
    }

    public List<String> getThemes() {
        return themes;
    }

    public void setThemes(List<String> themes) {
        this.themes = themes;
    }

}
