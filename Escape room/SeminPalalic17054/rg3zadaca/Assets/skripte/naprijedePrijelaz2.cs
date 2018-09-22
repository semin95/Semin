using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class naprijedePrijelaz2 : MonoBehaviour {

    public void naprijed()
    {
        PlayerPrefs.SetString("Preostalo vrijeme", "" + 120);
        Application.LoadLevel(6);
    }
}
