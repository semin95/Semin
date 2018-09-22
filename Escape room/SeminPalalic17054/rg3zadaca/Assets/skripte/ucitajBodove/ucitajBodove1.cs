using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class ucitajBodove1 : MonoBehaviour {

    [SerializeField]
    private Text tekst = null;

    // Use this for initialization
    void Start()
    {
        tekst.text = "Score: " + PlayerPrefs.GetString("Bodovi staze");
    }

    // Update is called once per frame
    void Update()
    {

    }
}
