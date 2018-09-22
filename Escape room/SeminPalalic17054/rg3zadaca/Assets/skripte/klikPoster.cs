using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class klikPoster : MonoBehaviour {

    private void OnMouseDown()
    {
        PlayerPrefs.SetString("Preostalo vrijeme", "" + 25);
        Application.LoadLevel(11);
    }
}
