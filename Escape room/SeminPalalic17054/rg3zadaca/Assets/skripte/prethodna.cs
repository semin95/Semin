using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using System.IO;
using LitJson;
using System;

public class prethodna : MonoBehaviour {
    private string JsonString;
    private JsonData podaci;
    public void cont()
    {
        JsonString = File.ReadAllText(Application.dataPath + "/Resursi/Igra.json");
        podaci = JsonMapper.ToObject(JsonString);

        try
        {
            PlayerPrefs.SetString("Score", "" + (int)podaci["score"]);
            PlayerPrefs.SetString("Username", (string)podaci["username"]);
            PlayerPrefs.SetString("Preostalo vrijeme", "" + (double)podaci["vrijeme"]);
            int id = (int)podaci["scena"];
            Application.LoadLevel(id);
        }
        catch (Exception e)
        {

        }
    }
}
