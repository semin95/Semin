using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using System.IO;
using LitJson;

public class readJson : MonoBehaviour {

    private string JsonString;
    private JsonData podaci;
	// Use this for initialization
	void Start () {
        JsonString = File.ReadAllText(Application.dataPath + "/Resursi/highscore.json");
        podaci = JsonMapper.ToObject(JsonString);

        string prvi = (string)podaci[0]["username"] + " " + podaci[0]["score"];
        string drugi = (string)podaci[1]["username"] + " " + podaci[1]["score"];
        string treci = (string)podaci[2]["username"] + " " + podaci[2]["score"];
        string cetvrti = (string)podaci[3]["username"] + " " + podaci[3]["score"];
        string peti = (string)podaci[4]["username"] + " " + podaci[4]["score"];


        PlayerPrefs.SetString("prvi", prvi);
        PlayerPrefs.SetString("drugi", drugi);
        PlayerPrefs.SetString("treci", treci);
        PlayerPrefs.SetString("cetvrti", cetvrti);
        PlayerPrefs.SetString("peti", peti);
    }
	
	// Update is called once per frame
	void Update () {
		
	}
}
