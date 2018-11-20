package edu.upc.eetac.dsa;

import org.apache.log4j.Logger;
import sun.security.util.Length;

import java.util.*;

public class MyBikeImpl implements MyBike {
    // singleton
    final static Logger logger = Logger.getLogger(MyBikeImpl.class.getName());
    private static MyBikeImpl instance;
    private ArrayList<Station> stations;
    private HashMap<String, User> userList;




    public void addUser(String idUser, String name, String surname)
    {
        logger.info("Creating an User");
        this.userList.put(idUser,new User(idUser, name, surname));
        logger.info("created User : "+name+surname);
    }

    public void addStation(String idStation, String description, int max, double lat, double lon)
    {
        logger.info("Creating a Station");
        this.stations.add(new Station(idStation,description,max,lat,lon));
        logger.info("created Station : "+idStation);
    }

    public List<User> getUserList() {
        List<User> aux = new ArrayList<>();
        aux.addAll(userList.values());
        return aux;
    }

    public void addBike(String idBike, String description, double kms, String idStation) throws StationFullException, StationNotFoundException
    {
        logger.info("Adding a bike");
        boolean b=true;
        for (Station st:stations) {
            if (st.getIdStation().equals(idStation)) {
                b = false;
                if (st.getMax() == st.getListBikes().size()) {
                    throw new StationFullException();

                } else
                    st.addBike(new Bike(idBike, description, kms));
                logger.info("Bike " + idBike + " added in station " + idStation);
            }
        }
           if (b)
                throw new StationNotFoundException();


    }

    private MyBikeImpl(){
        userList = new HashMap<>();
        stations = new ArrayList<>();

    }
    public static MyBikeImpl getInstance(){
        if(instance==null){
            instance = new MyBikeImpl();
        }
        return instance;
    }
    public List<Bike> bikesByStationOrderByKms(String idStation) throws StationNotFoundException
    {
        logger.info("Sorting Bikes by kms in the Station"+idStation);
        boolean b=true;
        LinkedList<Bike> BikesSortedByKm = new LinkedList<>();
        for (Station s:stations) {
            if (s.getIdStation().equals(idStation)) {
                b = false;
                List<Bike> bikesOfStation = s.getListBikes();
                for (Bike p : bikesOfStation) {
                    BikesSortedByKm.add(p);
                }
                Collections.sort(BikesSortedByKm, (p1, p2) -> (int) (p1.getKms() - p2.getKms()));

            }
        }
            if (b)
                throw new StationNotFoundException();
        logger.info("Sorted!");
        return BikesSortedByKm;

    }
    public Bike getBike (String stationId,String userId) throws UserNotFoundException, StationNotFoundException
    {
        logger.info("Getting a bike for User: "+userId+"from station: "+ stationId);
        Bike res = null;
        boolean a=true;
        boolean b=true;
        for (Station s:stations) {
            if (s.getIdStation().equals(stationId)) {
                b = false;
                res = s.getListBikes().pop();
                for (User u : getUserList()) {
                    if (u.getIdUser().equals(userId)) {
                        a = false;
                        u.addBike(res);
                    }
                }
                    if (a)
                        throw new UserNotFoundException();
            }
        }
        if (b)
            throw new StationNotFoundException();
            return res;
    }

    public List<Bike> bikesByUser(String userId) throws UserNotFoundException
    {
        logger.info("getting bikes from user: " + userId);
        LinkedList<Bike> listBikesUser = new LinkedList<>();
        boolean a=true;
        List<User> users = getUserList();
        for (User u : users) {
            if (u.getIdUser().equals(userId)) {
                a = false;
                listBikesUser = u.getBikesUserList();
            }
        }
        if (a)
            throw new UserNotFoundException();

        logger.info("The bikes of user" + userId + "are " + listBikesUser);
        return listBikesUser;
    }
    public int numUsers()
    {
        logger.info("Getting number of Users");
        int numUs= userList.size();
        logger.info("Number of Users:"+numUs);
        return numUs;

    }
    public int numStations()
    {
        logger.info("Getting number of Stations");
        int numSt=stations.size();
        logger.info("Number of Stations:"+numSt);
        return numSt;

    }
    public int numBikes(String idStation) throws StationNotFoundException
    {
        logger.info("Getting number of Bikes");
        int res=0;
        boolean a=true;
        for (Station s:stations) {
            if (s.getIdStation().equals(idStation)) {
                a = false;
                res = s.getListBikes().size();
            }
        }
            if (a)
                throw new StationNotFoundException();
        logger.info("Number of Bikes:" + res);
        return res;
    }


    public void clear() {
        this.stations.clear();
        this.userList.clear();
        logger.info("DB cleared");
    }
}
