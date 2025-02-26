import React, { useState } from "react";
import { Image, ImageBackground, ScrollView, Text, TouchableOpacity, View } from "react-native";
import { SafeAreaView } from "react-native-safe-area-context";
import { globalStyles } from "../globalStyles";

import thebatmanBackground from '../assets/films/thebatman-background.jpg';
import thebatmanPoster from '../assets/films/thebatman-poster.jpg';

import rate from '../assets/icons/add.png';
import list from '../assets/icons/list.png';
import watchList from '../assets/icons/watchlistButton.png';

import paulDano from '../assets/actors/paul-dano.jpg';
import robertPattinson from '../assets/actors/robert-pattinson.jpg';
import zoeKravitz from '../assets/actors/zoe-kravitz.jpg';

const reviews = [
  { name: "David", image: robertPattinson, review: "The Batman is a great movie. I loved the action scenes and the plot. The actors were amazing and the soundtrack was perfect." },
  { name: "David", image: robertPattinson, review: "The Batman is a great movie. I loved the action scenes and the plot. The actors were amazing and the soundtrack was perfect." },
  { name: "David", image: robertPattinson, review: "The Batman is a great movie. I loved the action scenes and the plot. The actors were amazing and the soundtrack was perfect." },
  { name: "David", image: robertPattinson, review: "The Batman is a great movie. I loved the action scenes and the plot. The actors were amazing and the soundtrack was perfect." },
  { name: "David", image: robertPattinson, review: "The Batman is a great movie. I loved the action scenes and the plot. The actors were amazing and the soundtrack was perfect." }
]

export default function Film() {
  const [activeButton, setActiveButton] = useState("casts");

  const handleButtonPress = (buttonName) => {
    setActiveButton(buttonName);
  }

  return (
    <ScrollView vertical={true} showsVerticalScrollIndicator={false} style={{ backgroundColor: "#1F1D36" }} contentContainerStyle={{ paddingBottom: 10}}>
      <ImageBackground source={thebatmanBackground} style={styles.imageBackground}>
        {/* <SafeAreaView style={styles.overlay}>
          <View style={styles.backButton}>
            <Text style={styles.backArrow}>←</Text>
          </View>
        </SafeAreaView> */}
      </ImageBackground>

      <SafeAreaView style={[globalStyles.container, styles.mainContainer]}>
        <View style={styles.filmHeader}>
          <Image style={styles.poster} source={thebatmanPoster} />

          <View style={styles.textContainer}>
            <Text style={[globalStyles.textBase, styles.filmTitle]}>
              The Batman <Text style={styles.filmYear}>2022</Text>
            </Text>
            <Text style={[globalStyles.textBase, styles.filmDirector]}>
              Directed by <Text style={{ fontWeight: "bold" }}>Matt Reeves</Text>
            </Text>
          </View>
        </View>

        <View style={styles.body}>
          <View style={styles.buttons}>
            <View style={styles.button}>
              <Image style={styles.buttonImage} source={rate}/>
              <Text style={[globalStyles.textBase, styles.buttonText]}>Rate</Text>
            </View>
            <View style={styles.button}>
              <Image style={styles.buttonImage} source={list}/>
              <Text style={[globalStyles.textBase, styles.buttonText]}>Add to Lists</Text>
            </View>
            <View style={styles.button}>
              <Image style={styles.buttonImage} source={watchList}/>
              <Text style={[globalStyles.textBase, styles.buttonText]}>Add to Watchlist</Text>
            </View>
          </View>

          <View style={styles.description}>
            <Text style={[globalStyles.textBase, styles.descriptionText]}>
              UNMASK THE TRUTH.
              In his second year of fighting crime, Batman uncovers corruption in Gotham City that connects to his own family while facing a serial killer known as the Riddler.</Text>
          </View>
        </View>

        <View style={styles.castContainer}>
          <View style={styles.buttonOptions}>
            <TouchableOpacity
              style={[styles.buttonCast, activeButton === 'casts' && styles.buttonCastActive]}
              onPress={() => handleButtonPress('casts')}
            >
              <Text style={[globalStyles.textBase, styles.buttonCastText]}>Casts</Text>
            </TouchableOpacity>
            <TouchableOpacity
              style={[styles.buttonCast, activeButton === 'watch' && styles.buttonCastActive]}
              onPress={() => handleButtonPress('watch')}
            >
              <Text style={[globalStyles.textBase, styles.buttonCastText]}>Watch</Text>
            </TouchableOpacity>
          </View>

          <ScrollView horizontal={true} showsHorizontalScrollIndicator={false}>
            <View style={styles.cast}>
              <Image style={styles.castImage} source={robertPattinson} />
              <Image style={styles.castImage} source={zoeKravitz} />
              <Image style={styles.castImage} source={paulDano} />
              <Image style={styles.castImage} source={robertPattinson} />
              <Image style={styles.castImage} source={zoeKravitz} />
              <Image style={styles.castImage} source={paulDano} />
              <Image style={styles.castImage} source={robertPattinson} />
              <Image style={styles.castImage} source={zoeKravitz} />
              <Image style={styles.castImage} source={paulDano} />
            </View>
          </ScrollView>
        </View>

        <View style={styles.reviews}>
          <View style={styles.reviewTitles}>
            <Text style={[globalStyles.textBase]}>All reviews</Text>
            <Text style={[globalStyles.textBase, styles.seeAll]}>See all</Text>
          </View>

          {reviews.map((review, index) => (
            <View key={index} style={styles.reviewContainer}>
              <View style={styles.reviewHeader}>
                <Image style={styles.reviewImageUser} source={review.image}/>
                <Text style={[globalStyles.textBase, styles.reviewText]}>Review by <Text style={[globalStyles.textBase, styles.reviewTextUser]}>{review.name}</Text></Text>
              </View>
              <Text style={[globalStyles.textBase, styles.reviewResult]}>{review.review}</Text>
            </View>
          ))}
        </View>
      </SafeAreaView>
    </ScrollView>
  );
}

const styles = {
  imageBackground: {
    height: 320,
    width: '100%',
    justifyContent: 'flex-start',
  },

  overlay: {
    flex: 1,
    justifyContent: 'center',
    paddingHorizontal: 15,
  },

  backButton: {
    backgroundColor: "rgba(0, 0, 0, 0.5)",
    width: 40,
    height: 40,
    borderRadius: 20,
    justifyContent: "center",
    alignItems: "center",
    marginTop: -180,
  },

  backArrow: {
    color: "#fff",
    fontSize: 24,
  },

  mainContainer: {
    paddingHorizontal: 15,
    paddingTop: 10,
  },

  filmHeader: {
    flexDirection: "row",
    alignItems: "center",
  },

  poster: {
    width: 150,
    height: 200,
    borderRadius: 10,
    marginTop: -150,
  },

  textContainer: {
    flex: 1,
    marginTop: -80,
    marginLeft: 20,
  },

  filmTitle: {
    fontSize: 22,
    fontWeight: "bold",
    color: "#fff",
  },

  filmYear: {
    fontSize: 14,
    fontWeight: "normal",
    color: "#aaa",
  },

  filmDirector: {
    fontSize: 12,
    marginTop: 5,
  },

  body: {
    flex: 1,
    flexDirection: "row",
    marginTop: 10,
  },

  buttons: {
    flex: 1,
    flexDirection: "column",
    justifyContent: "space-between",
  },

  button: {
    flexDirection: "row",
    alignItems: "center",
    marginBottom: 10,
    backgroundColor: "#E9A6A6",
    width: 150,
    height: 35,
    borderRadius: 10,
  },

  buttonImage: {
    width: 20,
    height: 20,
    marginLeft: 10,
  },

  buttonText: {
    marginLeft: 8,
    color: "#000",
    fontSize: 12,
  },

  description: {
    flex: 1,
  },
  
  descriptionText: {
    fontSize: 14,
    color: "#fff",
    marginTop: -35,
    marginLeft: -10,
    textAlign: "justify",
  },

  castContainer: {
    marginTop: 20,
  },

  buttonOptions: {
    flexDirection: "row",
    gap: 10,
    marginBottom: 20,
  },

  buttonCastActive: {
    width: 70,
    alignItems: "center",
    justifyContent: "center",
    backgroundColor: "#9C4A8B",
    padding: 8,
    borderRadius: 20,
  },

  buttonCast: {
    width: 70,
    alignItems: "center",
    justifyContent: "center",
    backgroundColor: "#323048",
    padding: 8,
    borderRadius: 20,
    borderWidth: 1,
    borderColor: "#fff",
    borderColor: "rgba(255, 255, 255, 0.5)",
  },

  buttonCastText: {
    fontSize: 14,
  },

  cast: {
    flexDirection: "row",
    gap: 15
  },

  castImage: {
    width: 60,
    height: 60,
    borderRadius: 50,
  },

  reviews: {
    marginTop: 20,
  },

  reviewTitles: {
    flexDirection: "row",
    justifyContent: "space-between",
  },

  seeAll: {
    fontSize: 14,
    color: "#E9A6A6",
  },

  reviewContainer: {
    marginTop: 10,
    backgroundColor: "#323048",
    borderRadius: 10,
    padding: 10,
  },

  reviewHeader: {
    flexDirection: "row",
    alignItems: "center",
  },

  reviewImageUser: {
    width: 50,
    height: 50,
    borderRadius: 50,
  },

  reviewText: {
    color: "rgba(255, 255, 255, 0.5)",
    marginLeft: 10,
    fontSize: 12
  },

  reviewTextUser: {
    color: "#E9A6A6",
    opacity: 1,
    fontSize: 12
  },

  reviewResult: {
    color: "#fff",
    marginLeft: 60,
    fontSize: 14,
    marginTop: -8,
  }
};
