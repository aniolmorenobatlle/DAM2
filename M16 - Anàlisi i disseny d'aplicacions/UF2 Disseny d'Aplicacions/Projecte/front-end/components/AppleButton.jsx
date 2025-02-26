import { Image, Text, View } from 'react-native';

import appleLogo from '../assets/logos/appleLogo.png';

export default function AppleButton({ text }) {
  return (
    <View style={styles.mainButton}>
      <Image
        style={styles.logo}
        source={appleLogo}
      />        
      <Text style={styles.text}>{ text }</Text>
    </View>
  );
}

const styles = {
  mainButton: {
    flexDirection: "row",
    alignItems: "center",
    justifyContent: "center",
    width: 350,
    height: 45,
    backgroundColor: "#000",
    borderRadius: 20,
    borderWidth: 1,
    borderColor: "#DADCE0",
  },

  logo: {
    width: 25,
    height: 25,
    marginLeft: 10,
  },

  text: {
    color: "#fff",
    flex: 1,
    textAlign: "center",
    fontSize: 14,
  }
}
