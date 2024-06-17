package com.codedev.modernfarmer.Callbacks;

import com.codedev.modernfarmer.Classes.Veterinarians;

import java.util.List;

public interface OnVetsReceived {

    void onVetsDataReceived(List<Veterinarians> veterinariansList);
}
