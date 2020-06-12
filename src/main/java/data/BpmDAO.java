package data;
// Gemmer de data der er fra DTO.
public interface BpmDAO { //DAO = DATA ACCESS OBJEKT - DVS det objekt der tilgår data
    void save(BpmDTO bpmDTO);
}
