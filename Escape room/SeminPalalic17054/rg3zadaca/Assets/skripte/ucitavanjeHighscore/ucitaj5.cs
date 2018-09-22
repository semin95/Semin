using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class ucitaj5 : MonoBehaviour
{

    [SerializeField]
    private Text tekst5 = null;

    // Use this for initialization
    void Start()
    {
        tekst5.text = PlayerPrefs.GetString("peti");
    }

    // Update is called once per frame
    void Update()
    {

    }
}

