package property.editor;

import java.beans.PropertyEditorSupport;

public class HouseEditor extends PropertyEditorSupport {

    public void setAsText(String text) {
        if (text == null || text.indexOf(",") == -1) {
            throw new IllegalArgumentException("设置的字符串格式不正确");
        }
        String[] infos = text.split(",");
        House house = new House();
        house.setArea(infos[0]);
        house.setPosition(Integer.parseInt(infos[1]));
        house.setPrice(Double.parseDouble(infos[2]));
        setValue(house);
    }
} 