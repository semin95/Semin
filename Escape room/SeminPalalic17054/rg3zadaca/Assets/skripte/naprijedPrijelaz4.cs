using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class naprijedPrijelaz4 : MonoBehaviour {

	public void naprijed()
    {
        PlayerPrefs.SetString("Preostalo vrijeme", "" + 20);
        Application.LoadLevel(15);
    }
}
