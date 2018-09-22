using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using System.IO;
using LitJson;
using System;

public class vrijemeScena1 : MonoBehaviour {

    double preostaloVrijeme;
    int score;
    int bodoviStaze = 200;
    private GUIStyle guiStyle = new GUIStyle();
    private Light[] lights;
    bool stop = false;


    private string JsonString;
    private JsonData podaci;
    public Highscore1 h1;
    public Highscore1 h2;
    public Highscore1 h3;
    public Highscore1 h4;
    public Highscore1 h5;

    // Use this for initialization
    void Start () {

        score = 0;

        lights = FindObjectsOfType(typeof(Light)) as Light[];
        foreach (Light light in lights)
        {
            light.intensity = 0;
        }

        PlayerPrefs.SetString("Bodovi staze", "" + bodoviStaze);
        PlayerPrefs.SetString("Score", "" + 0);

        preostaloVrijeme = double.Parse(PlayerPrefs.GetString("Preostalo vrijeme"));

        JsonString = File.ReadAllText(Application.dataPath + "/Resursi/highscore.json");
        podaci = JsonMapper.ToObject(JsonString);

        h1 = new Highscore1((int)podaci[0]["id"], (string)podaci[0]["username"], (int)podaci[0]["score"]);
        h2 = new Highscore1((int)podaci[1]["id"], (string)podaci[1]["username"], (int)podaci[1]["score"]);
        h3 = new Highscore1((int)podaci[2]["id"], (string)podaci[2]["username"], (int)podaci[2]["score"]);
        h4 = new Highscore1((int)podaci[3]["id"], (string)podaci[3]["username"], (int)podaci[3]["score"]);
        h5 = new Highscore1((int)podaci[4]["id"], (string)podaci[4]["username"], (int)podaci[4]["score"]);

    }

    JsonData igra;

    // Update is called once per frame
    void Update () {
        if (!stop)
        {
            preostaloVrijeme -= Time.deltaTime;
        }
        if (Input.GetKeyUp("o"))
        {
            foreach (Light light in lights)
            {
                light.intensity = 1;
            }
            stop = true;

            //poziv panela u drugoj metodi treba pozvati ovo
            
            PlayerPrefs.SetString("Score", "" + (int)(bodoviStaze + (int)preostaloVrijeme));
            PlayerPrefs.SetString("Preostalo vrijeme", "" + 30);
            Application.LoadLevel(8);
        }

        if (Input.GetKeyUp(KeyCode.Escape))
        {
            string username = PlayerPrefs.GetString("Username");
            int score = Int32.Parse(PlayerPrefs.GetString("Score"));
            Igra i = new Igra(3, username, score, preostaloVrijeme);
            igra = JsonMapper.ToJson(i);
            File.WriteAllText(Application.dataPath + "/Resursi/Igra.json", igra.ToString());
            Application.LoadLevel(16);
        }
    }

    private bool provjereno = false;
    void OnGUI()
    {
        if (preostaloVrijeme > 0)
        {
            guiStyle.fontSize = 16;
            guiStyle.normal.textColor = Color.gray;
            GUI.Label(new Rect(650, 10, 200, 20), "Preostalo vrijeme: " + (int)preostaloVrijeme, guiStyle);
            GUI.Label(new Rect(10, 10, 100, 20), "Score: 0", guiStyle);
            guiStyle.fontSize = 40;
            GUI.Label(new Rect(250, 150, 300, 40), "Turn On lights!", guiStyle);
            PlayerPrefs.SetString("Preostalo vrijeme", "" + preostaloVrijeme);
        }
        else
        {

            if (!provjereno)
            {
                upisiUHighscore();
                provjereno = true;
            }

            Application.LoadLevel(12);

        }
    }

    JsonData highscore;

    public void upisiUHighscore()
    {
        if (score > h1.score)
        {
            h5 = h4;
            h4 = h3;
            h3 = h2;
            h2 = h1;

            h1 = new Highscore1(0, PlayerPrefs.GetString("Username"), score);
        }
        else if (score > h2.score)
        {
            h5 = h4;
            h4 = h3;
            h3 = h2;

            h2 = new Highscore1(1, PlayerPrefs.GetString("Username"), score);
        }
        else if (score > h3.score)
        {
            h5 = h4;
            h4 = h3;

            h3 = new Highscore1(2, PlayerPrefs.GetString("Username"), score);
        }
        else if (score > h4.score)
        {
            h5 = h4;

            h4 = new Highscore1(3, PlayerPrefs.GetString("Username"), score);
        }
        else if (score > h5.score)
        {
            h5 = new Highscore1(4, PlayerPrefs.GetString("Username"), score);
        }


        Highscore1[] niz = new Highscore1[5] { h1, h2, h3, h4, h5 };
        highscore = JsonMapper.ToJson(niz);
        File.WriteAllText(Application.dataPath + "/Resursi/Highscore.json", highscore.ToString());
    }

}

public class Highscore1
{
    public int id;
    public string username;
    public int score;

    public Highscore1(int id, string username, int score)
    {
        this.id = id;
        this.username = username;
        this.score = score;
    }
}

public class Igra
{
    public int scena;
    public string username;
    public int score;
    public double vrijeme;

    public Igra(int scena , string username , int score , double vrijeme)
    {
        this.scena = scena;
        this.username = username;
        this.score = score;
        this.vrijeme = vrijeme;
    }
}
