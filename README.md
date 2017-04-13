# BinBeats
Software for creation of binaural beats
Version 1 - 15.04.2017 


### Table Of Contents

- [Safety Notice](#safety-notice)
- [System Requirements](#system-requirements)
- [Installation](#installation)
- [How It Works](#how-it-works)
- [Usage](#usage)
- [Known issues](#known-issues)
- [Collaborating On This Project](#collaborating-on-this-project)
- [Third Party Content](#third-party-content)

## Safety Notice
People with brain diseases like epilepsy, stroke patients or people with heart rhythm disorder should not use binaural beats or should at least seek medical advice before usage. Binaural beats influence brain waves and can thereby have undesirable effects on people with medical history.

## System Requirements
Thanks to the application being written in Java it is mostly platform independent. You only need mouse, keyboard, stereo headphones and the current Java runtime environment (JRE).

## Installation
Get the executable .jar file [here](https://github.com/AlexNitter/BinBeats/raw/master/build/BinBeats.jar). No further installation steps are necessary. Just execute the application by double-clicking the .jar file.

## How It Works
Binaural beats are an auditive phenomenon where slightly different frequencies played for the left and the right ear produce the mean tone that pulsates with the difference frequency.
If you play a tone of 200 Hz on your left ear and 210 Hz on your right ear your brain's auditive system will perceive a tone of 205 Hz that pulsates with 10 Hz. 

Binaural Beats are often accredited with a distinct effect on brain wave patterns. Certain mental states are associated with specific brain wave patterns. Playing a binaural beat that correlates with that pattern can produce the associated mental state and allows the user to utilize various beneficial effects. The existence of said effects is debatable and not scientifically proven.

A table that clearly presents the mental states and their associated effects can be found on [Wikipedia](https://en.wikipedia.org/wiki/Electroencephalography#Normal_activity).

## Usage
- "Carrier Frequency" is the base pitch of the sine tones to be played.
- "Beat Frequency" describes the difference between the two tones and the frequency with which the binaural beat will pulsate. 

1. Set up the binaural beat through the text fields and sliders or select a preset from the dropdown field.
2. Play the currently configured binaural beat by clicking on the "Play" symbol.
3. Save the currently configured binaural beat by clicking on the "Save" symbol.

Move the mouse cursor over UI elements to display explanatory tooltips.

## Known Issues
- The beat will keep on playing for a second after the stop button is clicked
- Decreasing the volume will make the tone less "sine-y", e.g. it will sound more like a square wave
- ... to be continued

## Collaborating On This Project
If you are a developer and are interested in this project you are invited to fork it and develop it further. We are currently not actively developing this project so please don't send pull requests.

## Third Party Content
* The UI uses [MiG Java Layout Manager](http://miglayout.com/) under [3-Clause BSD License](https://opensource.org/licenses/BSD-3-Clause).
* The button icons were designed by [Madebyoliver](http://www.flaticon.com/authors/madebyoliver) from [Flaticon](http://www.flaticon.com/packs/essential-collection).
