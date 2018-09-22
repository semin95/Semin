using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using System.IO;
using LitJson;

public class spasavanje : MonoBehaviour {

    JsonData igra;
    private string JsonString;
    private JsonData podaci;

    public void Save_Game()
    {
        Application.LoadLevel(0);
    }

    public void exit()
    {
        Igra i = new Igra(-1, "", 0, 0);
        igra = JsonMapper.ToJson(i);
        File.WriteAllText(Application.dataPath + "/Resursi/Igra.json", igra.ToString());
        Application.LoadLevel(0);
    }

    public void cont()
    {
        JsonString = File.ReadAllText(Application.dataPath + "/Resursi/Igra.json");
        podaci = JsonMapper.ToObject(JsonString);

        Debug.Log("ime:" + podaci["scena"]);

        PlayerPrefs.SetString("Score","" +  (int)podaci["score"]);
        PlayerPrefs.SetString("Username", (string)podaci["username"]);
        PlayerPrefs.SetString("Preostalo vrijeme","" + (double)podaci["vrijeme"]);
        int id = (int)podaci["scena"];
        Application.LoadLevel(id);
    }

}
