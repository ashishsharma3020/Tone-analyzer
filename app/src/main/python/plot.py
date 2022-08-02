import io
import matplotlib.pyplot as plt
import json
from ibm_watson import ToneAnalyzerV3
from ibm_cloud_sdk_core.authenticators import IAMAuthenticator
import requests


def plot(text):

    authenticator = IAMAuthenticator('2_w0vbrNXIK0YTFieGzk7Ayc8kjxfd_v4AHrIcBBlp5P')
    tone_analyzer = ToneAnalyzerV3(
        version='2017-09-21',
        authenticator=authenticator
    )
    #key  =2_w0vbrNXIK0YTFieGzk7Ayc8kjxfd_v4AHrIcBBlp5P
    # url=https://api.au-syd.tone-analyzer.watson.cloud.ibm.com/instances/42019e98-60a1-48a6-9367-058b1d3e9ce6
    tone_analyzer.set_service_url('https://api.au-syd.tone-analyzer.watson.cloud.ibm.com/instances/42019e98-60a1-48a6-9367-058b1d3e9ce6')
    tone_analyzer.set_disable_ssl_verification(True)
    tone_analysis = tone_analyzer.tone(
        {'text': text},
        content_type='application/json', sentences=False
    ).get_result()
    headers = {
        'x-rapidapi-host': "genius.p.rapidapi.com",
        'x-rapidapi-key': "4200aacc10mshfa051a5a471d54dp168aecjsnac3cbe406bed"
        }
    lst=tone_analysis['document_tone']['tones']
    dlist=list()
    for t in lst:
       res = requests.get(url="https://genius.p.rapidapi.com/search",params={
       "q":t['tone_name']
       },headers=headers)
       data=json.loads(res.text)
       lss=data["response"]
       ls=lss["hits"]
       for x in ls:
            tm={
                      "name":x["result"]["title"],
                      "artist":x["result"]["artist_names"],
                      "imageurl":x["result"]["header_image_thumbnail_url"],
                      "songurl":"https://genius.com/songs/"+str(x["result"]["id"])+"/apple_music_player",
                      "artisturl":x["result"]["primary_artist"]["url"]

                  }
            dlist.append(tm)


    return json.dumps(dlist, indent=2)


