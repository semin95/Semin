using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class klikNaDevet : MonoBehaviour {

    private void OnMouseDown()
    {
        PlayerPrefs.SetString("Preostalo vrijeme", "" + 15);
        Application.LoadLevel(10);
    }

    // Use this for initialization
    void Start () {
		
	}
	
	// Update is called once per frame
	void Update () {
		
	}
}
