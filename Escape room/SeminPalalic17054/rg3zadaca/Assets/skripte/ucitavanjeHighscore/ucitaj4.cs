using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class ucitaj4 : MonoBehaviour
{

    [SerializeField]
    private Text tekst4 = null;

    // Use this for initialization
    void Start()
    {
        tekst4.text = PlayerPrefs.GetString("cetvrti");
    }

    // Update is called once per frame
    void Update()
    {

    }
}
