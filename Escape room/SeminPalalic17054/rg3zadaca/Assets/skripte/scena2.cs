using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using System.IO;
using LitJson;
using System;

public class scena2 : MonoBehaviour {

    double preostaloVrijeme;
    int score;
    int bodoviStaze = 300;
    private GUIStyle guiStyle = new GUIStyle();


    private string JsonString;
    private JsonData podaci;
    public Highscore h1;
    public Highscore h2;
    public Highscore h3;
    public Highscore h4;
    public Highscore h5;

    // Use this for initialization
    void Start () {
        score = System.Convert.ToInt32(PlayerPrefs.GetString("Score"));

        PlayerPrefs.SetString("Bodovi staze", "" + bodoviStaze);

        preostaloVrijeme = double.Parse(PlayerPrefs.GetString("Preostalo vrijeme"));

        JsonString = File.ReadAllText(Application.dataPath + "/Resursi/highscore.json");
        podaci = JsonMapper.ToObject(JsonString);

        h1 = new Highscore((int)podaci[0]["id"], (string)podaci[0]["username"], (int)podaci[0]["score"]);
        h2 = new Highscore((int)podaci[1]["id"], (string)podaci[1]["username"], (int)podaci[1]["score"]);
        h3 = new Highscore((int)podaci[2]["id"], (string)podaci[2]["username"], (int)podaci[2]["score"]);
        h4 = new Highscore((int)podaci[3]["id"], (string)podaci[3]["username"], (int)podaci[3]["score"]);
        h5 = new Highscore((int)podaci[4]["id"], (string)podaci[4]["username"], (int)podaci[4]["score"]);
    }
    JsonData igra;
    // Update is called once per frame
    void Update () {
        preostaloVrijeme -= Time.deltaTime;

        if (Input.GetKeyUp(KeyCode.Escape))
        {
            string username = PlayerPrefs.GetString("Username");
            int score = Int32.Parse(PlayerPrefs.GetString("Score"));
            score = score - bodoviStaze - (int)preostaloVrijeme;
            Debug.Log(score);
            Igra i = new Igra(4, username, score, preostaloVrijeme);
            igra = JsonMapper.ToJson(i);
            File.WriteAllText(Application.dataPath + "/Resursi/Igra.json", igra.ToString());
            Application.LoadLevel(16);
        }

    }

    private bool provjereno = false;
    int ukupniScore;
    void OnGUI()
    {
        if (preostaloVrijeme > 0)
        {
            guiStyle.fontSize = 16;
            guiStyle.normal.textColor = Color.white;
            GUI.Label(new Rect(650, 10, 200, 20), "Preostalo vrijeme: " + (int)preostaloVrijeme, guiStyle);
            GUI.Label(new Rect(10, 10, 100, 20), "Score: " + score, guiStyle);
            guiStyle.normal.textColor = Color.red;
            guiStyle.fontSize = 30;
            GUI.Label(new Rect(350, 320, 100, 20), "1 + 8", guiStyle);
            PlayerPrefs.SetString("Preostalo vrijeme", "" + preostaloVrijeme);
            ukupniScore = (int)bodoviStaze + (int)preostaloVrijeme + score;
            PlayerPrefs.SetString("Score", "" + ukupniScore);
        }
        else
        {
            PlayerPrefs.SetString("Score", "" + score);
               
            if (!provjereno)
            {
                provjereno = true;
                upisiUHighscore();
            }

            Application.LoadLevel(12);

        }
    }
    JsonData highscore;

    public void upisiUHighscore()
    {

        if(score > h1.score)
        {
            h5 = h4;
            h4 = h3;
            h3 = h2;
            h2 = h1;

            h1 = new Highscore(0, PlayerPrefs.GetString("Username"), score);
        }
        else if(score > h2.score)
        {
            h5 = h4;
            h4 = h3;
            h3 = h2;

            h2 = new Highscore(1, PlayerPrefs.GetString("Username"), score);
        }
        else if (score > h3.score)
        {
            h5 = h4;
            h4 = h3;

            h3 = new Highscore(2, PlayerPrefs.GetString("Username"), score);
        }
        else if (score > h4.score)
        {
            h5 = h4;

            h4 = new Highscore(3, PlayerPrefs.GetString("Username"), score);
        }
        else if (score > h5.score)
        {
            h5 = new Highscore(4, PlayerPrefs.GetString("Username"), score);
        }


        Highscore[] niz = new Highscore[5] { h1, h2, h3 , h4 ,h5 };
        highscore = JsonMapper.ToJson(niz);
        File.WriteAllText(Application.dataPath + "/Resursi/Highscore.json" , highscore.ToString());
        
    }


}

public class Highscore
{
    public int id;
    public string username;
    public int score;

    public Highscore(int id , string username , int score)
    {
        this.id = id;
        this.username = username;
        this.score = score;
    }
}
