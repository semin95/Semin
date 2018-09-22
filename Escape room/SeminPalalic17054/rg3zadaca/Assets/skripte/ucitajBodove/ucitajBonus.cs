using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class ucitajBonus : MonoBehaviour {

    [SerializeField]
    private Text tekst = null;

    // Use this for initialization
    void Start()
    {
        string p = PlayerPrefs.GetString("Preostalo vrijeme");
        tekst.text = "Bonus: " + vratiBroj(p);
    }

    string vratiBroj(string broj)
    {
        int i = 0;
        for(i = 0; i < broj.Length; i++)
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
