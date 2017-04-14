# BinBeats
Software for creation of binaural beats
Version 1 - 15.04.2017 


### Table Of Contents

- [Safety Notice](#safety-notice)
- [System Requirements](#system-requirements)
- [Installation](#installation)
- [How It Works](#how-it-works)
- [Usage](#usage)
- [Usage Scenarios](#usage-scenarios)
- [Known issues](#known-issues)
- [Collaborating On This Project](#collaborating-on-this-project)
- [Third Party Content](#third-party-content)

## Safety Notice
People with brain diseases like epilepsy, stroke patients or people with heart rhythm disorder should not use binaural beats or should at least seek medical advice before usage. Binaural beats influence brain waves and can thereby have undesirable effects on people with medical history.

## System Requirements
Thanks to the application being written in Java it is mostly platform independent. You only need mouse, keyboard, stereo headphones and the current Java runtime environment (JRE).

## Installation
Get the executable .jar file [here](https://github.com/AlexNitter/BinBeats/raw/master/build/BinBeats.jar). No further installation steps are necessary. Just execute the application by double-clicking the .jar file. On a Linux-System you may have to run the command "java -jar BinBeats.jar" in the console.

## How It Works
Binaural beats are an auditive phenomenon where slightly different frequencies played for the left and the right ear produce the mean tone that pulsates with the difference frequency.
If you play a tone of 200 Hz on your left ear and 210 Hz on your right ear your brain's auditive system will perceive a tone of 205 Hz that pulsates with 10 Hz. 

Binaural Beats are often accredited with a distinct effect on brain wave patterns. Certain mental states are associated with specific brain wave patterns. Playing a binaural beat that correlates with that pattern can produce the associated mental state and allows the user to utilize various beneficial effects. The existence of said effects is debatable and not scientifically proven.

A table that clearly presents the mental states and their associated effects can be found on [Wikipedia](https://en.wikipedia.org/wiki/Electroencephalography#Normal_activity).

## Usage
- "Carrier Frequency" is the base pitch of the sine tones to be played.
- "Beat Frequency" describes the difference between the two tones and the frequency with which the binaural beat will pulsate. 

1. Set up the binaural beat through the text fields and sliders or select a preset from the drop-down field.
2. Play the currently configured binaural beat by clicking on the "Play" symbol.
3. Save the currently configured binaural beat by clicking on the "Save" symbol.

Move the mouse cursor over UI elements to display explanatory tooltips.

## Usage Scenarios

### Play Binaural Beats Using Presets
 
You open the drop-down menu of presets with a click on the black triangle in the field "Preset". Choose a preset by clicking on it. After this step you can play your current configuration of the binaural beat by clicking the "play" symbol. By clicking the "stop" symbol, the tone can be stopped. 

### Regulate and play binaural beats by using sliders

The sliders for frequencies and volume regulation can be adjusted by dragging and dropping the slider. Bring the sliders in the wanted position and play your current configuration of the binaural beat by clicking the "play" symbol. By clicking the "stop" symbol, the tone can be stopped. 

### Regulate and play binaural beats by using input fields

Frequencies and volume can also be configured via input fields. Click in the editable field and enter your values for frequencies and volume. Confirm your configuration with the enter key. Play your current configuration of the binaural beat by clicking the "play" symbol. By clicking the "stop" symbol, the tone can be stopped. 

The ranges of values for the input fields are restricted as follows:

- value range for Carrier Frequency: 20 - 1500
- value range for Beat Frequency: 0.5 - 30
- value range for Volume: 0 - 100
 
### save current beat settings

You can choose a name for your binaural beats and save them. Initially configure the beat as you want to save it. With a click on the editable field "Preset" you can enter a name for your beat, which can be used to find it again after saving it. Save your current configuration of the binaural beat by clicking the "save" symbol. 
 
Previously saved binaural beats can be deleted. Select the setting in the drop-down menu of the "Preset" field with a click and confirm the execution of the action by clicking the "delete" symbol.

The name under which the configuration of a binaural beat is saved needs to be unique.

The presets given by the system ("Falling Asleep", "Trance", "Deep Meditation", "Creativity", "Concentrated Learning", "Problem Solving") cannot be deleted.

## Known Issues
- Sometimes you may hear an unobtrusive "click" while playing a beat
- changes of the setting may change after restart the playing (you need to play "stop" and "start" again)
- Decreasing the volume will make the tone less "sine-y", e.g. it will sound more like a square wave
- The beat will keep on playing for a second after the stop button is clicked


## Collaborating On This Project
If you are a developer and are interested in this project you are invited to fork it and develop it further. We are currently not actively developing this project so please don't send pull requests.

## Third Party Content
* The UI uses [MiG Java Layout Manager](http://miglayout.com/) under [3-Clause BSD License](https://opensource.org/licenses/BSD-3-Clause).
* The button icons were designed by [Madebyoliver](http://www.flaticon.com/authors/madebyoliver) from [Flaticon](http://www.flaticon.com/packs/essential-collection).
