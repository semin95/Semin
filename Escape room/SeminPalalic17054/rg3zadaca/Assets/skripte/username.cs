using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class username : MonoBehaviour {

	public void nazad_Username()
    {
        Application.LoadLevel(0);
    }

    public void naprijed_Username()
    {
        PlayerPrefs.SetString("Preostalo vrijeme","" +  120);
        Application.LoadLevel(3);
    }

    public void dajInput(string username)
    {
        PlayerPrefs.SetString("Username", username);
    }
}
