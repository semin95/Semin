using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class naprijedPrijelaz3 : MonoBehaviour {

    public void naprijed()
    {
        PlayerPrefs.SetString("Preostalo vrijeme", "" + 300);
        Application.LoadLevel(9);
    }
}
