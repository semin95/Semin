using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using System.IO;
using LitJson;
using System;
public class password : MonoBehaviour {

    string sifra;
    int score;
    private string JsonString;
    private JsonData podaci;
    public Highscore5 h1;
    public Highscore5 h2;
    public Highscore5 h3;
    public Highscore5 h4;
    public Highscore5 h5;

    private void Start()
    {
        score = System.Convert.ToInt32(PlayerPrefs.GetString("Score"));

        JsonString = File.ReadAllText(Application.dataPath + "/Resursi/highscore.json");
        podaci = JsonMapper.ToObject(JsonString);

        h1 = new Highscore5((int)podaci[0]["id"], (string)podaci[0]["username"], (int)podaci[0]["score"]);
        h2 = new Highscore5((int)podaci[1]["id"], (string)podaci[1]["username"], (int)podaci[1]["score"]);
        h3 = new Highscore5((int)podaci[2]["id"], (string)podaci[2]["username"], (int)podaci[2]["score"]);
        h4 = new Highscore5((int)podaci[3]["id"], (string)podaci[3]["username"], (int)podaci[3]["score"]);
        h5 = new Highscore5((int)podaci[4]["id"], (string)podaci[4]["username"], (int)podaci[4]["score"]);
    }

    public void nazad()
    {
        double p = double.Parse(PlayerPrefs.GetString("Preostalo vrijeme"));
        score = score - (int)p - 1000;
        PlayerPrefs.SetString("Score", "" + score);
        Application.LoadLevel(9);
        
    }

    public void naprijed()
    {
        try
        {
            if (sifra.Equals("1892"))
            {
                upisiUHighscore();

                Application.LoadLevel(14);
            }
            else
            {
                double p = double.Parse(PlayerPrefs.GetString("Preostalo vrijeme"));
                score = score - (int)p - 1000;
                PlayerPrefs.SetString("Score", "" + score);
                Application.LoadLevel(9);
            }
        }
        catch(Exception e)
        {

        }
    }

    public void dajInput(string s)
    {
        sifra = s;
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

            h1 = new Highscore5(0, PlayerPrefs.GetString("Username"), score);
        }
        else if (score > h2.score)
        {
            h5 = h4;
            h4 = h3;
            h3 = h2;

            h2 = new Highscore5(1, PlayerPrefs.GetString("Username"), score);
        }
        else if (score > h3.score)
        {
            h5 = h4;
            h4 = h3;

            h3 = new Highscore5(2, PlayerPrefs.GetString("Username"), score);
        }
        else if (score > h4.score)
        {
            h5 = h4;

            h4 = new Highscore5(3, PlayerPrefs.GetString("Username"), score);
        }
        else if (score > h5.score)
        {
            h5 = new Highscore5(4, PlayerPrefs.GetString("Username"), score);
        }


        Highscore5[] niz = new Highscore5[5] { h1, h2, h3, h4, h5 };
        highscore = JsonMapper.ToJson(niz);
        File.WriteAllText(Application.dataPath + "/Resursi/Highscore.json", highscore.ToString());

    }

}

public class Highscore5
{
    public int id;
    public string username;
    public int score;

    public Highscore5(int id, string username, int score)
    {
        this.id = id;
        this.username = username;
        this.score = score;
    }
}
