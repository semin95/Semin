using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class ucitaj3 : MonoBehaviour
{

    [SerializeField]
    private Text tekst3 = null;

    // Use this for initialization
    void Start()
    {
        tekst3.text = PlayerPrefs.GetString("treci");
    }

    // Update is called once per frame
    void Update()
    {

    }
}
