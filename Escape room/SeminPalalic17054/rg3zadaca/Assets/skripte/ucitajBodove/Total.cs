using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using System;
using System.IO;
using LitJson;

public class Total : MonoBehaviour {

    [SerializeField]
    private Text tekst = null;
    JsonData igra;

    // Use this for initialization
    void Start()
    {
        Igra i = new Igra(-1, "", 0, 0);
        igra = JsonMapper.ToJson(i);
        File.WriteAllText(Application.dataPath + "/Resursi/Igra.json", igra.ToString());

        string bodoviStaze = vratiBroj(PlayerPrefs.GetString("Bodovi staze"));
        string preostaloVrijeme = vratiBroj(PlayerPrefs.GetString("Preostalo vrijeme"));
        int total = Int32.Parse(bodoviStaze) + Int32.Parse(preostaloVrijeme);
        tekst.text = "Total: " + total;
    }

    string vratiBroj(string broj)
    {
        int i = 0;
        for (i = 0; i < broj.Length; i++)
        {
            if (broj[i] == '.')
                break;
        }
        return broj.Substring(0, i);
    }

    // Update is called once per frame
    void Update()
    {

    }
}
