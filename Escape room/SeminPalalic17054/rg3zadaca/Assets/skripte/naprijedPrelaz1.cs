using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class naprijedPrelaz1 : MonoBehaviour {

	public void naprijed()
    {
        PlayerPrefs.SetString("Preostalo vrijeme", "" + 90);
        Application.LoadLevel(4);
    }
}
