using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class ucitaj1 : MonoBehaviour {

    [SerializeField]
    private Text tekst = null;

    // Use this for initialization
    void Start()
    {
        tekst.text = PlayerPrefs.GetString("prvi");
    }
	
	// Update is called once per frame
	void Update () {
		
	}
}
