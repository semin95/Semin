using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class ucitaj2 : MonoBehaviour
{

    [SerializeField]
    private Text tekst2 = null;

    // Use this for initialization
    void Start()
    {
        tekst2.text = PlayerPrefs.GetString("drugi");
    }

    // Update is called once per frame
    void Update()
    {

    }
}
