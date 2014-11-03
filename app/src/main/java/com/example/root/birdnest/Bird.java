package com.example.root.birdnest;


public class Bird {

    public int id;
    public String ScientificName, FamilyName, LocalName, MalayName, Family, Location, Image, Sound;

    public Bird(){}

    // Bird Overload
    public Bird(String SciName, String FN, String LN, String MN, String family, String location, String Img, String Sound ){
        super();
        this.ScientificName = SciName;
        this.FamilyName = FN;
        this.LocalName = LN;
        this.MalayName = MN;
        this.Family = family;
        this.Location =location;
        this.Image = Img;
        this.Sound = Sound;

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String ScientificName){
        this.ScientificName = ScientificName;
    }

    public void setFamilyName(String FamilyName){
        this.FamilyName = FamilyName;
    }

    public void setMalayName(String malayName) {
        MalayName = malayName;
    }

    public void setScientificName(String scientificName) {
        ScientificName = scientificName;
    }

    public void setFamily(String family) {
        Family = family;
    }

    public void setLocalName(String localName) {
        LocalName = localName;
    }

    public void setLocation(String location){
        this.Location = location;
    }
    public void setImage(String image){
        this.Image = image;
    }
    public void setSound(String sound){
        this.Sound = sound;
    }

    @Override

    // For Logging Purpose
    public String toString() {
        return "Bird [id]:" + this.id+
                " [Sci]: "+this.ScientificName+
                " [FN]: " + this.FamilyName +
                " [LN]: " + this.LocalName +
                " [MN]: " + this.MalayName+
                " [Family]" + this.Family+
                " [Image]"+ this.Image+
                " [Sound]"+ this.Sound;
    }
}
