/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poppulate;

/**
 *
 * @author alex
 */
public class Tag {
    
    private String tagID;
    private String tagValue;

    public Tag(String tagID, String tagValue) {
        this.tagID = tagID;
        this.tagValue = tagValue;
    }

    public String getTagID() {
        return tagID;
    }

    public String getTagValue() {
        return tagValue;
    }

    @Override
    public String toString() {
        return "Tag{" + "tagID=" + tagID + ", tagValue=" + tagValue + '}';
    }
    
    
}
