package ga.gaba.ChemBoi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by glyczak on 2/24/18.
 */
public class ThermoCompoundSearcher {
    private static ThermoCompoundSearcher instance;
    private List<ThermoCompound> compounds;

    private ThermoCompoundSearcher() {
        String json = "[{\"name\":\"(COOH)2 (s)\",\"enthalpy\":-826.8,\"entropy\":120.0,\"freeEnergy\":-697.9},{\"name\":\"(NH4)2SO4 (s)\",\"enthalpy\":-1179.3,\"entropy\":220.3,\"freeEnergy\":-900.35},{\"name\":\"Ag (g)\",\"enthalpy\":289.2,\"entropy\":172.892,\"freeEnergy\":250.4},{\"name\":\"Ag (s)\",\"enthalpy\":0.0,\"entropy\":42.702,\"freeEnergy\":0.0},{\"name\":\"AgCl (s)\",\"enthalpy\":-127.03,\"entropy\":96.11,\"freeEnergy\":-109.72},{\"name\":\"AgNO2 (s)\",\"enthalpy\":-44.371,\"entropy\":128.1,\"freeEnergy\":19.85},{\"name\":\"AgNO3 (s)\",\"enthalpy\":-123.1,\"entropy\":140.9,\"freeEnergy\":-37.2},{\"name\":\"Al (g)\",\"enthalpy\":314.0,\"entropy\":164.44,\"freeEnergy\":273.0},{\"name\":\"Al (s)\",\"enthalpy\":0.0,\"entropy\":28.32,\"freeEnergy\":0.0},{\"name\":\"Al2O3 (s)\",\"enthalpy\":-1669.8,\"entropy\":50.986,\"freeEnergy\":-1576.4},{\"name\":\"Al 3+ (aq)\",\"enthalpy\":-524.7,\"entropy\":-313.0,\"freeEnergy\":-481.2},{\"name\":\"AlCl3 (s)\",\"enthalpy\":-653.4,\"entropy\":167.0,\"freeEnergy\":-636.8},{\"name\":\"Ar (g)\",\"enthalpy\":0.0,\"entropy\":154.7,\"freeEnergy\":0.0},{\"name\":\"As (g)\",\"enthalpy\":253.7,\"entropy\":174.1,\"freeEnergy\":212.3},{\"name\":\"As (s, gray metal)\",\"enthalpy\":0.0,\"entropy\":35.0,\"freeEnergy\":0.0},{\"name\":\"As4 (s)\",\"enthalpy\":149.0,\"entropy\":289.0,\"freeEnergy\":105.0},{\"name\":\"B (g)\",\"enthalpy\":406.0,\"entropy\":153.34,\"freeEnergy\":363.0},{\"name\":\"B (s)\",\"enthalpy\":0.0,\"entropy\":6.53,\"freeEnergy\":0.0},{\"name\":\"B2H6 (g)\",\"enthalpy\":31.0,\"entropy\":232.9,\"freeEnergy\":82.8},{\"name\":\"B2O3 (s)\",\"enthalpy\":-1264.0,\"entropy\":54.02,\"freeEnergy\":-1184.0},{\"name\":\"B5H9 (g)\",\"enthalpy\":62.8,\"entropy\":275.6,\"freeEnergy\":166.0},{\"name\":\"Ba (g)\",\"enthalpy\":175.6,\"entropy\":170.28,\"freeEnergy\":144.8},{\"name\":\"Ba (s)\",\"enthalpy\":0.0,\"entropy\":67.0,\"freeEnergy\":0.0},{\"name\":\"Ba 2+ (aq)\",\"enthalpy\":-538.36,\"entropy\":13.0,\"freeEnergy\":-560.7},{\"name\":\"BaCl2 • 2H2O (s)\",\"enthalpy\":-1461.7,\"entropy\":203.0,\"freeEnergy\":-1296.0},{\"name\":\"BaCl2 • H2O (s)\",\"enthalpy\":-1165.0,\"entropy\":167.0,\"freeEnergy\":-1059.0},{\"name\":\"BaCl2 (s)\",\"enthalpy\":-860.06,\"entropy\":126.0,\"freeEnergy\":-810.9},{\"name\":\"BBr3 (g)\",\"enthalpy\":-187.0,\"entropy\":324.2,\"freeEnergy\":-213.0},{\"name\":\"BBr3 (l)\",\"enthalpy\":-221.0,\"entropy\":229.0,\"freeEnergy\":-219.0},{\"name\":\"BCl3 (g)\",\"enthalpy\":-395.0,\"entropy\":289.9,\"freeEnergy\":-380.0},{\"name\":\"BCl3 (l)\",\"enthalpy\":-418.4,\"entropy\":209.0,\"freeEnergy\":-379.0},{\"name\":\"Be (g)\",\"enthalpy\":320.6,\"entropy\":136.17,\"freeEnergy\":282.8},{\"name\":\"Be (s)\",\"enthalpy\":0.0,\"entropy\":9.54,\"freeEnergy\":0.0},{\"name\":\"BF3 (g)\",\"enthalpy\":-1110.0,\"entropy\":254.0,\"freeEnergy\":-1093.0},{\"name\":\"BF4 (aq)\",\"enthalpy\":-1527.0,\"entropy\":167.0,\"freeEnergy\":-1435.0},{\"name\":\"Br (g)\",\"enthalpy\":111.8,\"entropy\":174.913,\"freeEnergy\":82.38},{\"name\":\"Br - (aq)\",\"enthalpy\":-120.9,\"entropy\":80.71,\"freeEnergy\":-102.82},{\"name\":\"Br2 (g)\",\"enthalpy\":30.7,\"entropy\":245.35,\"freeEnergy\":3.14},{\"name\":\"Br2 (l)\",\"enthalpy\":0.0,\"entropy\":152.3,\"freeEnergy\":0.0},{\"name\":\"C (g)\",\"enthalpy\":718.384,\"entropy\":157.99,\"freeEnergy\":672.975},{\"name\":\"C (s, diamond)\",\"enthalpy\":1.896,\"entropy\":2.439,\"freeEnergy\":2.866},{\"name\":\"C (s, graphite)\",\"enthalpy\":0.0,\"entropy\":5.694,\"freeEnergy\":0.0},{\"name\":\"C2H2 (g)\",\"enthalpy\":226.75,\"entropy\":200.82,\"freeEnergy\":209.0},{\"name\":\"C2H4 (g)\",\"enthalpy\":52.283,\"entropy\":219.5,\"freeEnergy\":68.124},{\"name\":\"C2H5OH (g)\",\"enthalpy\":-235.4,\"entropy\":282.0,\"freeEnergy\":-168.6},{\"name\":\"C2H5OH (l)\",\"enthalpy\":-277.63,\"entropy\":161.0,\"freeEnergy\":-174.8},{\"name\":\"C2H6 (g)\",\"enthalpy\":-84.667,\"entropy\":229.5,\"freeEnergy\":-32.88},{\"name\":\"C2O4 2- (aq)\",\"enthalpy\":-824.2,\"entropy\":51.0,\"freeEnergy\":-674.9},{\"name\":\"C3H8 (g)\",\"enthalpy\":-103.8,\"entropy\":269.9,\"freeEnergy\":-23.5},{\"name\":\"C6H12O6 (s)\",\"enthalpy\":-1260.0,\"entropy\":288.9,\"freeEnergy\":-919.2},{\"name\":\"C6H6 (g)\",\"enthalpy\":82.927,\"entropy\":269.2,\"freeEnergy\":129.65},{\"name\":\"C6H6 (l)\",\"enthalpy\":49.028,\"entropy\":172.8,\"freeEnergy\":124.5},{\"name\":\"Ca (g)\",\"enthalpy\":192.6,\"entropy\":154.8,\"freeEnergy\":158.9},{\"name\":\"Ca (s)\",\"enthalpy\":0.0,\"entropy\":41.6,\"freeEnergy\":0.0},{\"name\":\"Ca 2+ (aq)\",\"enthalpy\":-542.96,\"entropy\":-55.2,\"freeEnergy\":-553.04},{\"name\":\"CaCO3 (s, aragonite)\",\"enthalpy\":-1207.0,\"entropy\":88.7,\"freeEnergy\":-1127.7},{\"name\":\"CaCO3 (s, calcite)\",\"enthalpy\":-1206.9,\"entropy\":92.9,\"freeEnergy\":-1128.8},{\"name\":\"CH3CHO (g)\",\"enthalpy\":-166.4,\"entropy\":266.0,\"freeEnergy\":-133.7},{\"name\":\"CH3COOH (l)\",\"enthalpy\":-487.0,\"entropy\":160.0,\"freeEnergy\":-392.0},{\"name\":\"CH3NH2 (g)\",\"enthalpy\":-28.0,\"entropy\":241.5,\"freeEnergy\":28.0},{\"name\":\"CH3OCH3 (g)\",\"enthalpy\":-184.05,\"entropy\":267.1,\"freeEnergy\":-112.92},{\"name\":\"CH3OH (aq)\",\"enthalpy\":-245.9,\"entropy\":132.3,\"freeEnergy\":-175.2},{\"name\":\"CH3OH (g)\",\"enthalpy\":-201.3,\"entropy\":236.0,\"freeEnergy\":-161.9},{\"name\":\"CH3OH (l)\",\"enthalpy\":-238.64,\"entropy\":127.0,\"freeEnergy\":-166.3},{\"name\":\"CH3SH (g)\",\"enthalpy\":-12.4,\"entropy\":254.8,\"freeEnergy\":0.88},{\"name\":\"CH4 (g)\",\"enthalpy\":-74.848,\"entropy\":186.2,\"freeEnergy\":-50.794},{\"name\":\"Cl (g)\",\"enthalpy\":121.39,\"entropy\":165.09,\"freeEnergy\":105.4},{\"name\":\"Cl - (aq)\",\"enthalpy\":-167.46,\"entropy\":55.2,\"freeEnergy\":-131.17},{\"name\":\"Cl2 (g)\",\"enthalpy\":0.0,\"entropy\":222.95,\"freeEnergy\":0.0},{\"name\":\"Cl2O (g)\",\"enthalpy\":76.15,\"entropy\":266.5,\"freeEnergy\":93.72},{\"name\":\"ClF3 (g)\",\"enthalpy\":-155.0,\"entropy\":278.7,\"freeEnergy\":-114.0},{\"name\":\"ClO2 (g)\",\"enthalpy\":103.0,\"entropy\":249.0,\"freeEnergy\":123.0},{\"name\":\"ClO2 - (aq)\",\"enthalpy\":-69.0,\"entropy\":101.0,\"freeEnergy\":-10.7},{\"name\":\"ClO3 - (aq)\",\"enthalpy\":-98.32,\"entropy\":163.0,\"freeEnergy\":-2.6},{\"name\":\"ClO4 - (aq)\",\"enthalpy\":-131.4,\"entropy\":182.0,\"freeEnergy\":-8.0},{\"name\":\"CO (g)\",\"enthalpy\":-110.523,\"entropy\":197.91,\"freeEnergy\":-137.268},{\"name\":\"CO2 (aq)\",\"enthalpy\":-412.9,\"entropy\":121.0,\"freeEnergy\":-386.2},{\"name\":\"CO2 (s)\",\"enthalpy\":-393.513,\"entropy\":213.64,\"freeEnergy\":-394.383},{\"name\":\"CO3 2- (aq)\",\"enthalpy\":-676.3,\"entropy\":-53.1,\"freeEnergy\":-528.1},{\"name\":\"Cs (g)\",\"enthalpy\":78.78,\"entropy\":175.49,\"freeEnergy\":51.21},{\"name\":\"Cs (s)\",\"enthalpy\":0.0,\"entropy\":82.8,\"freeEnergy\":0.0},{\"name\":\"Cs + (aq)\",\"enthalpy\":-248.0,\"entropy\":133.0,\"freeEnergy\":-282.0},{\"name\":\"CsBr (s)\",\"enthalpy\":-394.0,\"entropy\":121.0,\"freeEnergy\":-383.0},{\"name\":\"CsI (s)\",\"enthalpy\":-337.0,\"entropy\":130.0,\"freeEnergy\":-333.0},{\"name\":\"Cu (g)\",\"enthalpy\":341.0,\"entropy\":166.28,\"freeEnergy\":301.4},{\"name\":\"Cu (s)\",\"enthalpy\":0.0,\"entropy\":33.3,\"freeEnergy\":0.0},{\"name\":\"Cu + (aq)\",\"enthalpy\":51.9,\"entropy\":-26.0,\"freeEnergy\":50.2},{\"name\":\"Cu 2+ (aq)\",\"enthalpy\":64.39,\"entropy\":-98.7,\"freeEnergy\":64.98},{\"name\":\"CuSO4 (s)\",\"enthalpy\":-769.86,\"entropy\":113.0,\"freeEnergy\":-661.9},{\"name\":\"F (g)\",\"enthalpy\":76.6,\"entropy\":158.64,\"freeEnergy\":59.4},{\"name\":\"F - (aq)\",\"enthalpy\":-329.1,\"entropy\":-9.6,\"freeEnergy\":-276.4},{\"name\":\"F2 (g)\",\"enthalpy\":0.0,\"entropy\":203.0,\"freeEnergy\":0.0},{\"name\":\"Fe (g)\",\"enthalpy\":404.5,\"entropy\":100.3,\"freeEnergy\":358.8},{\"name\":\"Fe (s)\",\"enthalpy\":0.0,\"entropy\":27.2,\"freeEnergy\":0.0},{\"name\":\"Fe 2+ (aq)\",\"enthalpy\":-87.9,\"entropy\":-113.0,\"freeEnergy\":-84.93},{\"name\":\"Fe2O3 (s, hematite)\",\"enthalpy\":-822.1,\"entropy\":90.0,\"freeEnergy\":-741.0},{\"name\":\"Fe 3+ (aq)\",\"enthalpy\":-47.7,\"entropy\":-293.0,\"freeEnergy\":-10.6},{\"name\":\"Fe3O4 (s, magnetite)\",\"enthalpy\":-1121.0,\"entropy\":146.0,\"freeEnergy\":-1014.0},{\"name\":\"Ge (g)\",\"enthalpy\":328.2,\"entropy\":167.8,\"freeEnergy\":290.8},{\"name\":\"Ge (s)\",\"enthalpy\":0.0,\"entropy\":42.42,\"freeEnergy\":0.0},{\"name\":\"H (g)\",\"enthalpy\":217.94,\"entropy\":114.61,\"freeEnergy\":203.24},{\"name\":\"H + (aq)\",\"enthalpy\":0.0,\"entropy\":0.0,\"freeEnergy\":0.0},{\"name\":\"H2 (g)\",\"enthalpy\":0.0,\"entropy\":130.59,\"freeEnergy\":0.0},{\"name\":\"H2CO3 (aq)\",\"enthalpy\":-698.7,\"entropy\":191.0,\"freeEnergy\":-623.4},{\"name\":\"H2O (g)\",\"enthalpy\":-241.83,\"entropy\":188.72,\"freeEnergy\":-228.59},{\"name\":\"H2O (l)\",\"enthalpy\":-285.84,\"entropy\":69.94,\"freeEnergy\":-237.19},{\"name\":\"H2O2 (l)\",\"enthalpy\":-187.6,\"entropy\":92.0,\"freeEnergy\":-113.97},{\"name\":\"H2S (aq)\",\"enthalpy\":-39.0,\"entropy\":122.0,\"freeEnergy\":-27.4},{\"name\":\"H2S (g)\",\"enthalpy\":-20.15,\"entropy\":205.6,\"freeEnergy\":-33.02},{\"name\":\"H30 +(aq)\",\"enthalpy\":-285.84,\"entropy\":69.94,\"freeEnergy\":-237.19},{\"name\":\"HBr (g)\",\"enthalpy\":-36.2,\"entropy\":198.48,\"freeEnergy\":-53.22},{\"name\":\"HC2O4 - (aq)\",\"enthalpy\":-818.8,\"entropy\":154.0,\"freeEnergy\":-698.7},{\"name\":\"HCHO (g)\",\"enthalpy\":-116.0,\"entropy\":218.7,\"freeEnergy\":-110.0},{\"name\":\"HCl (aq)\",\"enthalpy\":-167.46,\"entropy\":55.2,\"freeEnergy\":-131.17},{\"name\":\"HCl (g)\",\"enthalpy\":-92.312,\"entropy\":186.68,\"freeEnergy\":-95.265},{\"name\":\"HClO (aq)\",\"enthalpy\":-116.4,\"entropy\":130.0,\"freeEnergy\":-79.956},{\"name\":\"HCO3 (aq)\",\"enthalpy\":-691.1,\"entropy\":95.0,\"freeEnergy\":-587.1},{\"name\":\"HCOO - (aq)\",\"enthalpy\":-410.0,\"entropy\":91.6,\"freeEnergy\":-334.7},{\"name\":\"HCOOH (aq)\",\"enthalpy\":-410.0,\"entropy\":164.0,\"freeEnergy\":-356.1},{\"name\":\"HCOOH (g)\",\"enthalpy\":-362.6,\"entropy\":251.0,\"freeEnergy\":-335.7},{\"name\":\"HCOOH (l)\",\"enthalpy\":-409.2,\"entropy\":129.0,\"freeEnergy\":-346.0},{\"name\":\"He (g)\",\"enthalpy\":0.0,\"entropy\":126.1,\"freeEnergy\":0.0},{\"name\":\"HF (g)\",\"enthalpy\":-269.0,\"entropy\":173.5,\"freeEnergy\":-271.0},{\"name\":\"Hg (g)\",\"enthalpy\":60.84,\"entropy\":174.9,\"freeEnergy\":31.8},{\"name\":\"Hg (l)\",\"enthalpy\":0.0,\"entropy\":77.4,\"freeEnergy\":0.0},{\"name\":\"Hg2Cl2 (s)\",\"enthalpy\":-264.9,\"entropy\":196.0,\"freeEnergy\":-210.7},{\"name\":\"HgCl2 (s)\",\"enthalpy\":-230.0,\"entropy\":144.0,\"freeEnergy\":-186.0},{\"name\":\"HI  (g)\",\"enthalpy\":26.0,\"entropy\":206.32,\"freeEnergy\":1.3},{\"name\":\"I (g)\",\"enthalpy\":106.61,\"entropy\":180.68,\"freeEnergy\":70.149},{\"name\":\"I - (aq)\",\"enthalpy\":-55.94,\"entropy\":109.4,\"freeEnergy\":-51.67},{\"name\":\"C4H10 (g)\",\"enthalpy\":-131.6,\"entropy\":294.6,\"freeEnergy\":-18.0},{\"name\":\"I2 (g)\",\"enthalpy\":62.241,\"entropy\":260.58,\"freeEnergy\":19.4},{\"name\":\"I2 (s)\",\"enthalpy\":0.0,\"entropy\":117.0,\"freeEnergy\":0.0},{\"name\":\"I3 - (aq)\",\"enthalpy\":-51.9,\"entropy\":174.0,\"freeEnergy\":-51.51},{\"name\":\"IBr (g)\",\"enthalpy\":40.8,\"entropy\":259.0,\"freeEnergy\":3.8},{\"name\":\"ICl (g)\",\"enthalpy\":18.0,\"entropy\":247.4,\"freeEnergy\":-5.52},{\"name\":\"ICl3 (s)\",\"enthalpy\":-88.3,\"entropy\":172.0,\"freeEnergy\":-22.6},{\"name\":\"K (g)\",\"enthalpy\":90.0,\"entropy\":160.23,\"freeEnergy\":61.17},{\"name\":\"K (s)\",\"enthalpy\":0.0,\"entropy\":63.6,\"freeEnergy\":0.0},{\"name\":\"K + (aq)\",\"enthalpy\":-251.2,\"entropy\":103.0,\"freeEnergy\":-282.2},{\"name\":\"KCl (g)\",\"enthalpy\":-219.0,\"entropy\":239.5,\"freeEnergy\":-235.0},{\"name\":\"KCl (s)\",\"enthalpy\":-435.87,\"entropy\":82.68,\"freeEnergy\":-408.32},{\"name\":\"Kr (g)\",\"enthalpy\":0.0,\"entropy\":164.0,\"freeEnergy\":0.0},{\"name\":\"Li (g)\",\"enthalpy\":155.1,\"entropy\":138.67,\"freeEnergy\":122.1},{\"name\":\"Li (s)\",\"enthalpy\":0.0,\"entropy\":28.0,\"freeEnergy\":0.0},{\"name\":\"Li + (aq)\",\"enthalpy\":-278.46,\"entropy\":14.0,\"freeEnergy\":-293.8},{\"name\":\"LiBr (s)\",\"enthalpy\":-350.3,\"entropy\":69.0,\"freeEnergy\":-339.9},{\"name\":\"LiCl (s)\",\"enthalpy\":-408.8,\"entropy\":55.2,\"freeEnergy\":-383.6},{\"name\":\"LiF (s)\",\"enthalpy\":-612.1,\"entropy\":35.9,\"freeEnergy\":-584.1},{\"name\":\"Mg (g)\",\"enthalpy\":150.0,\"entropy\":148.55,\"freeEnergy\":115.0},{\"name\":\"Mg (s)\",\"enthalpy\":0.0,\"entropy\":32.5,\"freeEnergy\":0.0},{\"name\":\"Mg 2+ (aq)\",\"enthalpy\":-461.96,\"entropy\":-118.0,\"freeEnergy\":-456.01},{\"name\":\"MgCl2 • 6H2O (s)\",\"enthalpy\":-2499.6,\"entropy\":366.0,\"freeEnergy\":-2115.6},{\"name\":\"MgCl2 (s)\",\"enthalpy\":-641.83,\"entropy\":89.5,\"freeEnergy\":-542.32},{\"name\":\"N (g)\",\"enthalpy\":472.646,\"entropy\":153.195,\"freeEnergy\":455.512},{\"name\":\"n-C4H10 (g)\",\"enthalpy\":-124.7,\"entropy\":310.0,\"freeEnergy\":-15.7},{\"name\":\"n-C8H18 (l)\",\"enthalpy\":-250.0,\"entropy\":360.8,\"freeEnergy\":6.48},{\"name\":\"N2 (g)\",\"enthalpy\":0.0,\"entropy\":191.49,\"freeEnergy\":0.0},{\"name\":\"N2O (g)\",\"enthalpy\":81.55,\"entropy\":220.0,\"freeEnergy\":103.6},{\"name\":\"N2O2 2- (aq)\",\"enthalpy\":-10.8,\"entropy\":28.0,\"freeEnergy\":138.0},{\"name\":\"N2O4 (g)\",\"enthalpy\":9.661,\"entropy\":304.3,\"freeEnergy\":98.286},{\"name\":\"N2O5 (s)\",\"enthalpy\":-41.8,\"entropy\":113.0,\"freeEnergy\":134.0},{\"name\":\"N 3- (aq)\",\"enthalpy\":252.0,\"entropy\":134.0,\"freeEnergy\":325.0},{\"name\":\"Na (g)\",\"enthalpy\":108.7,\"entropy\":153.62,\"freeEnergy\":71.11},{\"name\":\"Na (s)\",\"enthalpy\":0.0,\"entropy\":51.0,\"freeEnergy\":0.0},{\"name\":\"Na + (aq)\",\"enthalpy\":-239.66,\"entropy\":60.2,\"freeEnergy\":-261.87},{\"name\":\"Na2 (g)\",\"enthalpy\":142.1,\"entropy\":230.2,\"freeEnergy\":104.0},{\"name\":\"Na2CO3 (s)\",\"enthalpy\":-1131.0,\"entropy\":136.0,\"freeEnergy\":-1048.0},{\"name\":\"Na2O (s)\",\"enthalpy\":-416.0,\"entropy\":72.8,\"freeEnergy\":-377.0},{\"name\":\"Na2O2 (s)\",\"enthalpy\":-504.6,\"entropy\":66.9,\"freeEnergy\":-430.1},{\"name\":\"NaCl (s)\",\"enthalpy\":-411.0,\"entropy\":72.4,\"freeEnergy\":-384.03},{\"name\":\"NaF (s)\",\"enthalpy\":-569.0,\"entropy\":58.6,\"freeEnergy\":-541.0},{\"name\":\"Ne (g)\",\"enthalpy\":0.0,\"entropy\":144.1,\"freeEnergy\":0.0},{\"name\":\"NH2CONH2 (s)\",\"enthalpy\":-333.2,\"entropy\":104.6,\"freeEnergy\":-197.2},{\"name\":\"NH3 (aq)\",\"enthalpy\":-80.83,\"entropy\":110.0,\"freeEnergy\":-26.6},{\"name\":\"NH3 (g)\",\"enthalpy\":-46.19,\"entropy\":192.5,\"freeEnergy\":-16.64},{\"name\":\"NH4 + (aq)\",\"enthalpy\":-132.8,\"entropy\":112.8,\"freeEnergy\":-79.5},{\"name\":\"NH4Cl (s)\",\"enthalpy\":-315.4,\"entropy\":94.6,\"freeEnergy\":-203.9},{\"name\":\"NO (g)\",\"enthalpy\":90.374,\"entropy\":210.62,\"freeEnergy\":86.688},{\"name\":\"NO2 - (aq)\",\"enthalpy\":-106.0,\"entropy\":125.0,\"freeEnergy\":-34.5},{\"name\":\"NO2 (g)\",\"enthalpy\":33.85,\"entropy\":240.5,\"freeEnergy\":51.84},{\"name\":\"NO3 - (aq)\",\"enthalpy\":-206.57,\"entropy\":146.0,\"freeEnergy\":-110.6},{\"name\":\"O (g)\",\"enthalpy\":247.52,\"entropy\":160.95,\"freeEnergy\":230.09},{\"name\":\"O2 (g)\",\"enthalpy\":0.0,\"entropy\":205.03,\"freeEnergy\":0.0},{\"name\":\"O3 (g)\",\"enthalpy\":142.0,\"entropy\":238.0,\"freeEnergy\":163.4},{\"name\":\"OH (g)\",\"enthalpy\":42.09,\"entropy\":183.63,\"freeEnergy\":37.4},{\"name\":\"OH - (aq)\",\"enthalpy\":-229.94,\"entropy\":-10.5,\"freeEnergy\":-157.3},{\"name\":\"P (g)\",\"enthalpy\":314.5,\"entropy\":163.1,\"freeEnergy\":279.1},{\"name\":\"P (s, red)\",\"enthalpy\":-18.0,\"entropy\":29.0,\"freeEnergy\":-14.0},{\"name\":\"P (s, white)\",\"enthalpy\":0.0,\"entropy\":44.4,\"freeEnergy\":0.0},{\"name\":\"P4 (g)\",\"enthalpy\":54.89,\"entropy\":279.9,\"freeEnergy\":24.4},{\"name\":\"Pb (g)\",\"enthalpy\":193.9,\"entropy\":175.27,\"freeEnergy\":161.0},{\"name\":\"Pb (s)\",\"enthalpy\":0.0,\"entropy\":64.89,\"freeEnergy\":0.0},{\"name\":\"Pb 2+ (aq)\",\"enthalpy\":1.6,\"entropy\":21.0,\"freeEnergy\":-24.3},{\"name\":\"PbO (s, red)\",\"enthalpy\":-219.2,\"entropy\":67.8,\"freeEnergy\":-189.3},{\"name\":\"PbO (s, yellow)\",\"enthalpy\":-217.9,\"entropy\":69.5,\"freeEnergy\":-188.5},{\"name\":\"PbO2 (s)\",\"enthalpy\":-276.6,\"entropy\":76.6,\"freeEnergy\":-219.0},{\"name\":\"PCl3 (g)\",\"enthalpy\":-306.4,\"entropy\":311.7,\"freeEnergy\":-286.3},{\"name\":\"PCl5 (g)\",\"enthalpy\":-398.9,\"entropy\":353.0,\"freeEnergy\":-324.6},{\"name\":\"Rb (g)\",\"enthalpy\":85.81,\"entropy\":169.99,\"freeEnergy\":55.86},{\"name\":\"Rb (s)\",\"enthalpy\":0.0,\"entropy\":69.5,\"freeEnergy\":0.0},{\"name\":\"Rb + (aq)\",\"enthalpy\":-246.0,\"entropy\":125.0,\"freeEnergy\":-282.2},{\"name\":\"RbBr (s)\",\"enthalpy\":-389.2,\"entropy\":108.3,\"freeEnergy\":-378.1},{\"name\":\"RbF (s)\",\"enthalpy\":-551.9,\"entropy\":114.0,\"freeEnergy\":-520.0},{\"name\":\"RbI (s)\",\"enthalpy\":-328.0,\"entropy\":118.0,\"freeEnergy\":-326.0},{\"name\":\"Rn (g)\",\"enthalpy\":0.0,\"entropy\":176.1,\"freeEnergy\":0.0},{\"name\":\"S (g)\",\"enthalpy\":222.8,\"entropy\":167.72,\"freeEnergy\":182.3},{\"name\":\"S (s, monoclinic)\",\"enthalpy\":0.3,\"entropy\":32.6,\"freeEnergy\":0.096},{\"name\":\"S (s, rhombic)\",\"enthalpy\":0.0,\"entropy\":31.9,\"freeEnergy\":0.0},{\"name\":\"SO2 (g)\",\"enthalpy\":-296.9,\"entropy\":248.5,\"freeEnergy\":-300.4},{\"name\":\"SO3 (g)\",\"enthalpy\":-395.2,\"entropy\":256.2,\"freeEnergy\":-370.4},{\"name\":\"Si (g)\",\"enthalpy\":368.4,\"entropy\":167.86,\"freeEnergy\":323.9},{\"name\":\"Si (s)\",\"enthalpy\":0.0,\"entropy\":18.7,\"freeEnergy\":0.0},{\"name\":\"SiO (g)\",\"enthalpy\":-111.8,\"entropy\":206.1,\"freeEnergy\":-137.1},{\"name\":\"SiO2 (s, quartz)\",\"enthalpy\":-859.4,\"entropy\":41.84,\"freeEnergy\":-805.0},{\"name\":\"Sn (g)\",\"enthalpy\":300.0,\"entropy\":168.4,\"freeEnergy\":268.0},{\"name\":\"Sn (s, gray)\",\"enthalpy\":2.5,\"entropy\":44.8,\"freeEnergy\":4.6},{\"name\":\"Sn (s, white)\",\"enthalpy\":0.0,\"entropy\":51.5,\"freeEnergy\":0.0},{\"name\":\"SO (g)\",\"enthalpy\":79.58,\"entropy\":221.9,\"freeEnergy\":53.47},{\"name\":\"Sr (g)\",\"enthalpy\":164.0,\"entropy\":164.53,\"freeEnergy\":110.0},{\"name\":\"Sr (s)\",\"enthalpy\":0.0,\"entropy\":54.4,\"freeEnergy\":0.0},{\"name\":\"Sr 2+ (aq)\",\"enthalpy\":-545.51,\"entropy\":-39.0,\"freeEnergy\":-557.3},{\"name\":\"Ti (g)\",\"enthalpy\":469.0,\"entropy\":100.2,\"freeEnergy\":423.0},{\"name\":\"Ti (s)\",\"enthalpy\":0.0,\"entropy\":30.3,\"freeEnergy\":0.0},{\"name\":\"Ti2O3 (s)\",\"enthalpy\":-1536.0,\"entropy\":78.78,\"freeEnergy\":-1448.0},{\"name\":\"Ti3O5 (s)\",\"enthalpy\":-2443.0,\"entropy\":129.4,\"freeEnergy\":-2300.0},{\"name\":\"TiO2 (s, rutile III)\",\"enthalpy\":-912.1,\"entropy\":50.25,\"freeEnergy\":-852.7},{\"name\":\"TlI (s)\",\"enthalpy\":-124.0,\"entropy\":123.0,\"freeEnergy\":-124.0},{\"name\":\"TlI (g)\",\"enthalpy\":33.0,\"entropy\":274.0,\"freeEnergy\":-13.0},{\"name\":\"W (g)\",\"enthalpy\":843.5,\"entropy\":173.85,\"freeEnergy\":801.7},{\"name\":\"W (s)\",\"enthalpy\":0.0,\"entropy\":33.0,\"freeEnergy\":0.0},{\"name\":\"Xe (g)\",\"enthalpy\":0.0,\"entropy\":169.6,\"freeEnergy\":0.0},{\"name\":\"Zn (s)\",\"enthalpy\":0.0,\"entropy\":41.6,\"freeEnergy\":0.0},{\"name\":\"ZnO (s)\",\"enthalpy\":-348.0,\"entropy\":43.9,\"freeEnergy\":-318.2}]\n";
        Gson gson = new GsonBuilder().create();
        compounds = gson.fromJson(json, new TypeToken<ArrayList<ThermoCompound>>(){}.getType());
    }

    public static ThermoCompoundSearcher getInstance() {
        if(instance == null) {
            instance = new ThermoCompoundSearcher();
        }
        return instance;
    }

    public ThermoCompound search(String compoundString) {
        for(ThermoCompound compound : compounds) {
            if(compound.getName().startsWith(compoundString)) return compound;
        }
        return null;
    }

    public MessageEmbed fancySearch(String query) {
        EmbedBuilder builder = new BotEmbedBuilder();
        EquationFormatter formatter = new EquationFormatter();
        boolean found = false;
        for(ThermoCompound compound : compounds) {
            if(compound.getName().contains(query)) {
                found = true;
                /*
                builder.addField(formatter.formatCompound(compound.getName()),
                        String.format("%.3f; %.3f; %.3f",
                                compound.getEnthalpy(),
                                compound.getEntropy(),
                                compound.getFreeEnergy()),
                        false);
                */
                builder.addField(formatter.formatCompound(compound.getName()),
                        Float.toString(compound.getEnthalpy()) + " kJ/mol", true);
                builder.addField("", Float.toString(compound.getEntropy()) + " J/molK", true);
                builder.addField("", Float.toString(compound.getFreeEnergy()) + " kJ/mol", true);
            }
        }
        if(found) {
            builder.setTitle(String.format(":fire: Thermodynamic Search Results for \"%s\"", query), null);
            builder.setDescription("Enthalpy of Formation - **∆H°**; Standard Entropy - **S°**; Gibbs Free Energy - **∆G°**");
        } else {
            builder.setTitle(String.format("No Results Found for \"%s\"", query), null);
        }
        return builder.build();
    }
}
