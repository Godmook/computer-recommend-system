package com.godmook.computerrecommendsystem.Controller;

import com.godmook.computerrecommendsystem.Model.*;
import com.godmook.computerrecommendsystem.mapper.CombineMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.ibatis.annotations.Param;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.*;

@RestController
@RequestMapping("/combine")
public class CombineController {
    CombineMapper mapper;

    public CombineController(CombineMapper mapper) {
        this.mapper = mapper;
    }
    Integer getTotalUser(){return mapper.getUserCount();}
    Integer getTotalCombine(){return mapper.getTotalCount();}
    Integer getGPUPrice(int id){return mapper.getGPUPrice(id);}
    Integer getGPURANKING(int id){return mapper.getGPURank(id);}
    @RequestMapping("/insert")
    Integer InsertCombine(@RequestBody CombineJoinModel model){
        return mapper.insertData(model.getCpu_id(),
                model.getCpu_count(),
                model.getGpu_id(),
                model.getGpu_count(),
                model.getMainboard_id(),
                model.getMainboard_count(),
                model.getRam_id(),
                model.getRam_count(),
                model.getSsd_id(),
                model.getSsd_count(),
                model.getHdd_id(),
                model.getHdd_count(),
                model.getCase_id(),
                model.getCase_count(),
                model.getCooler_id(),
                model.getCooler_count(),
                model.getPower_id(),
                model.getPower_count(),
                model.getUser_id(),
                model.getTime());
    }
    @ApiOperation(value="조합에 따른 프로그램 적정률",notes="id에는 GPU 아이디(없으면 0), purpose는 1= 사무용, 2= 게임용, 3= 영상제작용")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "SUCCESS"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @RequestMapping("/rate/{id}/{purpose}")
    List<JSONObject> makeRate(@PathVariable("id")int id,@PathVariable("purpose")int purpose){
        int my_rank=0;
        if(id!=0)
            my_rank=getGPURANKING(id);
        int my_price=4500;
        if(id!=0)
            my_price=getGPUPrice(my_rank);
        List<JSONObject> obj = new LinkedList<>();
        if(purpose==1){
            String [] arr = {"한글","엑셀", "파워포인트"};
            for(int i=0;i<3;i++){
                JSONObject tmp= new JSONObject();
                tmp.put("name",arr[i]);
                tmp.put("rate",(double)120);
                obj.add(tmp);
            }
        }
        else if(purpose==2){
            String [] arr = {"리그오브레전드","피파온라인4", "오버워치", "배틀그라운드","사이버펑크 2077","로스트아크","발로란트"};
            int [] arr_p = {6494,6494, 6974, 11186,20452,11886,8152};
            for(int i=0;i<7;i++){
                JSONObject tmp = new JSONObject();
                tmp.put("name",arr[i]);
                double rate=(double)my_price/(double)arr_p[i]*100;
                if(rate>200)rate=200;
                tmp.put("rate",rate);
                obj.add(tmp);
            }
        }
        else if(purpose==3){
            String [] arr = {"포토샵","마야", "블랜더", "일러스트레이터","애프터이펙트","캐드(CAD)","스케치업"};
            int [] arr_p = {8057,8057, 7057, 11186,8057,9026,7057};
            for(int i=0;i<7;i++){
                JSONObject tmp = new JSONObject();
                tmp.put("name",arr[i]);
                double rate=(double)my_price/(double)arr_p[i]*100;
                if(rate>200)rate=200;
                tmp.put("rate",rate);
                obj.add(tmp);
            }
        }
        return obj;
    }
    @RequestMapping("/total")
    JSONObject getTotalSet(){
        int a= getTotalUser();
        int b= getTotalCombine();
        JSONObject obj = new JSONObject();
        a=150;
        b=784;
        obj.put("user",a);
        obj.put("count",b);
        return obj;
    }
    CombineWordModel getCPU(int id) {
        return mapper.getCPUModel(id);
    }

    CombineWordModel getGPU(int id) {
        return mapper.getGPUModel(id);
    }

    CombineWordModel getMainBoard(int id) {
        return mapper.getMBModel(id);
    }

    CombineWordModel getSSD(int id) {
        return mapper.getSSDModel(id);
    }

    CombineWordModel getHDD(int id) {
        return mapper.getHDDModel(id);
    }

    CombineWordModel getPower(int id) {
        return mapper.getPowerModel(id);
    }

    CombineWordModel getCooler(int id) {
        return mapper.getCoolerModel(id);
    }

    CombineWordModel getCase(int id) {
        return mapper.getCaseModel(id);
    }

    CombineWordModel getRam(int id) {
        return mapper.getRAMModel(id);
    }

    List<CPUCombineModel> getCombineCPU(int price, String name) {
        return mapper.getCombineCPU(price, name);
    }

    List<GPUCombineModel> getCombineGPU(int price) {
        return mapper.getCombineGPU(price);
    }

    List<MainBoardCombineModel> getCombineIntelMainBoard1200(int price, String name) {
        return mapper.getCombineMainBoard(price, name);
    }

    List<RamCombineModel> getCombineRam(int price) {
        return mapper.getCombineRam(price);
    }

    List<DefaultCombineModel> getCombineSSD(int price) {
        return mapper.getCombineSSD(price);
    }

    List<DefaultCombineModel> getCombineHDD(int price) {
        return mapper.getCombineHDD(price);
    }

    List<DefaultCombineModel> getCombineCase(int price) {
        return mapper.getCombineCase(price);
    }

    List<GPUCombineModel> getCombinePower(int price) {
        return mapper.getCombinePower(price);
    }

    List<DefaultCombineModel> getCombineCooler(int price) {
        return mapper.getCombineCooler(price);
    }


    List<Integer> getPowerID(int power) {
        return mapper.getLimitPower(power);
    }

    Integer getGPUPower(int id) {
        return mapper.getGpuPower(id);
    }
    String getBottleNeck(int id1,int id2){
        return mapper.getBottleneck(id1,id2);
    }
    @GetMapping("/getBottle/{id1}/{id2}")
    Integer readCSV(@PathVariable ("id1")int id1,@PathVariable("id2")int id2){
        MultiValueMap<Integer,HashMap<Integer,Integer>> result = new LinkedMultiValueMap<>();
        String[] tmp = {"소켓1200", "소켓1700", "AM4", "AM5"};
        List<GPUCombineModel> gpu = getCombineGPU(10000000);
        gpu.add(0,new GPUCombineModel());
        for(int i=0;i<4;i++) {
            List<CPUCombineModel> cpu = getCombineCPU(10000000, tmp[i]);
            for(CPUCombineModel cpu1:cpu){
                for(GPUCombineModel gpu1:gpu){
                    if(gpu1==null||cpu1==null)continue;
                    if(cpu1.getId()==0||gpu1.getId()==0)continue;
                    String as = getBottleNeck(cpu1.getId(), gpu1.getId());
                    HashMap<Integer,Integer> res1=new HashMap<>();
                    int tmp2=as.indexOf(".");
                    if(tmp2==-1){
                        res1.put(gpu1.getId(),Integer.parseInt(as.substring(0, as.indexOf("%"))));
                    }
                    else {
                        res1.put(gpu1.getId(), Integer.parseInt(as.substring(0, as.indexOf("."))));
                    }
                    result.add(cpu1.getId(),res1);
                    //System.out.println(cpu1.getId()+" " + gpu1.getId()+" "+ as);
                }
            }
        }
        //System.out.println(result.get(17913980));
        for(HashMap<Integer,Integer> e : result.get(id1)){
            if(e.get(id2)!=null)
                return e.get(id2);
        }
        return 0;
    }
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    @GetMapping("/{cpu}/{gpu}/{mb}/{ssd}/{hdd}/{cooler}/{case}/{power}/{ram}")
    JSONObject getTotalSet(@PathVariable("cpu") int id1,
                           @PathVariable("gpu") int id2,
                           @PathVariable("mb") int id3,
                           @PathVariable("ssd") int id4,
                           @PathVariable("hdd") int id5,
                           @PathVariable("cooler") int id6,
                           @PathVariable("case") int id7,
                           @PathVariable("power") int id8,
                           @PathVariable("ram") int id9
    ) {
        JSONObject jsonObj = new JSONObject();
        CombineWordModel tmp1 = getCPU(id1);
        CombineWordModel tmp2 = getGPU(id2);
        CombineWordModel tmp3 = getMainBoard(id3);
        CombineWordModel tmp4 = getSSD(id4);
        CombineWordModel tmp5 = getHDD(id5);
        CombineWordModel tmp6 = getCooler(id6);
        CombineWordModel tmp7 = getCase(id7);
        CombineWordModel tmp8 = getPower(id8);
        CombineWordModel tmp9 = getRam(id9);
        jsonObj.put("gpu", tmp2);
        jsonObj.put("cpu", tmp1);
        jsonObj.put("mainboard", tmp3);
        jsonObj.put("ssd", tmp4);
        jsonObj.put("hdd", tmp5);
        jsonObj.put("cooler", tmp6);
        jsonObj.put("case", tmp7);
        jsonObj.put("power", tmp8);
        jsonObj.put("ram", tmp9);
        return jsonObj;
    }

    List<CombineAIModel> GetAllCombine(int start,int end,int exp,int exp2){return mapper.getAllCombine(start,end,exp,exp2);}
    List<CombineAIModel> GetSpecificCombine(int start,int end, String socket,int exp,int exp2){return mapper.getSpecificCombine(start,end,socket,exp,exp2);}
    @ApiOperation(value="Flask 연동 컴퓨터 조합 제시",notes="start= 시작 금액, end= 종료 금액, cpu = 인텔, AMD, ANY 중 1개, purpose 사무, 게임, 그래픽 중 1")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "SUCCESS"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping("/csv/{start}/{end}/{cpu}/{purpose}")
    List<JSONObject> Setting(@PathVariable("start") int start, @PathVariable("end") int end, @PathVariable("cpu")String cpu_c,@PathVariable("purpose")int purpose){
        List<JSONObject> totalObj = new LinkedList<>();
        String map_1="https://shop.danawa.com/virtualestimate/?controller=estimateMain&methods=index&marketPlaceSeq=16&logger_kw=dnw_gnb_esti";
        if(cpu_c.equals("ANY")){
            List<CombineAIModel> tmp = new LinkedList<>();
            tmp=GetAllCombine(start,end,5*(3-purpose),5);
            for(CombineAIModel e: tmp) {
                JSONObject obj = new JSONObject();
                String map2 = "&productSeqList=";
                String map3 = "&quantityList=";
                obj.put("cpu_id", e.getCpu_id());
                map2 += e.getCpu_id();
                map2 += ",";
                obj.put("cpu_count", 1);
                map3 += "1,";
                obj.put("mainboard_id", e.getMainboard_id());
                obj.put("mainboard_count", 1);
                map2 += e.getMainboard_id();
                map2 += ",";
                map3 += "1,";
                obj.put("gpu_id", e.getGpu_id());
                if (e.getGpu_id()!= 0) {
                    obj.put("gpu_count", 1);
                    map2 += e.getGpu_id();
                    map2 += ",";
                    map3 += "1,";
                } else {
                    obj.put("gpu_count", 0);
                }

                obj.put("power_id", e.getPower_id());
                map2 += e.getPower_id();
                map2 += ",";
                obj.put("power_count", 1);
                map3 += "1,";
                obj.put("ssd_id", e.getSsd_id());
                map2 += e.getSsd_id();
                map2 += ",";
                obj.put("ssd_count", 1);
                map3 += "1,";
                obj.put("ram_id", e.getRam_id());
                map2 += e.getRam_id();
                map2 += ",";
                if(e.getGpu_id()==0){
                    obj.put("ram_count", 1);
                    map3+="1,";
                }
                else {
                    obj.put("ram_count",2);
                    map3 += "2,";
                }
                obj.put("case_id", e.getCase_id());
                map2 += e.getCase_id();
                map2 += ",";
                map3 += "1,";
                obj.put("case_count", 1);
                obj.put("cooler_id", e.getCooler_id());
                if (e.getCooler_id() != 0) {
                    map2 += e.getCooler_id();
                    map3 += "1";
                    obj.put("cooler_count", 1);
                } else
                    obj.put("cooler_count", 0);
                obj.put("total_price", e.getPrice());
                String total1 = map_1 + map2 + map3;
                obj.put("total_link", total1);
                obj.put("hdd_id",0);
                obj.put("hdd_count",0);
                obj.put("keyboard_id",0);
                obj.put("keyboard_count",0);
                obj.put("mouse_id",0);
                obj.put("mouse_count",0);
                obj.put("monitor_id",0);
                obj.put("monitor_count",0);
                totalObj.add(obj);
            }
        }
        else{
            List<CombineAIModel> tmp = new LinkedList<>();
            tmp=GetSpecificCombine(start,end,cpu_c,5*(3-purpose),5);
            for(CombineAIModel e: tmp) {
                JSONObject obj = new JSONObject();
                String map2 = "&productSeqList=";
                String map3 = "&quantityList=";
                obj.put("cpu_id", e.getCpu_id());
                map2 += e.getCpu_id();
                map2 += ",";
                obj.put("cpu_count", 1);
                map3 += "1,";
                obj.put("mainboard_id", e.getMainboard_id());
                obj.put("mainboard_count", 1);
                map2 += e.getMainboard_id();
                map2 += ",";
                map3 += "1,";
                obj.put("gpu_id", e.getGpu_id());
                if (e.getGpu_id()!= 0) {
                    obj.put("gpu_count", 1);
                    map2 += e.getGpu_id();
                    map2 += ",";
                    map3 += "1,";
                } else {
                    obj.put("gpu_count", 0);
                }

                obj.put("power_id", e.getPower_id());
                map2 += e.getPower_id();
                map2 += ",";
                obj.put("power_count", 1);
                map3 += "1,";
                obj.put("ssd_id", e.getSsd_id());
                map2 += e.getSsd_id();
                map2 += ",";
                obj.put("ssd_count", 1);
                map3 += "1,";
                obj.put("ram_id", e.getRam_id());
                map2 += e.getRam_id();
                map2 += ",";
                if(e.getGpu_id()==0){
                    obj.put("ram_count", 1);
                    map3+="1,";
                }
                else {
                    obj.put("ram_count",2);
                    map3 += "2,";
                }
                obj.put("case_id", e.getCase_id());
                map2 += e.getCase_id();
                map2 += ",";
                map3 += "1,";
                obj.put("case_count", 1);
                obj.put("cooler_id", e.getCooler_id());
                if (e.getCooler_id() != 0) {
                    map2 += e.getCooler_id();
                    map3 += "1";
                    obj.put("cooler_count", 1);
                } else
                    obj.put("cooler_count", 0);
                obj.put("total_price", e.getPrice());
                String total1 = map_1 + map2 + map3;
                obj.put("total_link", total1);
                obj.put("hdd_id",0);
                obj.put("hdd_count",0);
                obj.put("keyboard_id",0);
                obj.put("keyboard_count",0);
                obj.put("mouse_id",0);
                obj.put("mouse_count",0);
                obj.put("monitor_id",0);
                obj.put("monitor_count",0);
                totalObj.add(obj);
            }
        }
        return totalObj;
    }
    //purpose 1= 문서 2= 게임 3= 영상
    @ApiOperation(value="조합 제시",notes="cpu 에는 인텔, AMD 중 하나 삽입")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "SUCCESS"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping("/{start}/{end}/{cpu}")
    List<JSONObject> gegSet(@PathVariable("start") int start, @PathVariable("end") int end, @PathVariable("cpu")String cpu_c) {
        String[] tmp = {"소켓1200", "소켓1700", "AM4", "AM5"};
        String map_1="https://shop.danawa.com/virtualestimate/?controller=estimateMain&methods=index&marketPlaceSeq=16&logger_kw=dnw_gnb_esti";
        int count = 0;
        List<JSONObject> jsonTotal = new ArrayList<>();
        DefaultCombineModel[][] set2 = new DefaultCombineModel[43][2];
        for (int i = 0; i < 4; i++) {
            List<CPUCombineModel> cpu = getCombineCPU(10000000, tmp[i]);
            List<MainBoardCombineModel> mainboard = getCombineIntelMainBoard1200(10000000, tmp[i]);
            for (CPUCombineModel cpu1 : cpu) {
                for (MainBoardCombineModel mb1 : mainboard) {
                    if (cpu1.getSocket().equals(mb1.getSocket())) {
                        if(!cpu1.getSocket().contains(cpu_c))continue;
                        if (cpu1.getPrice() < 150000) {
                            if (mb1.getPrice() > 120000) {
                                continue;
                            }
                        }
                        if (cpu1.getPrice() < 200000) {
                            if (mb1.getPrice() > 150000) {
                                continue;
                            }
                        }
                        if (cpu1.getPrice() <500000 ) {
                            if (mb1.getPrice() > 300000) {
                                continue;
                            }
                        }
                        if (cpu1.getPrice() > 200000) {
                            if (mb1.getPrice() < 120000) {
                                continue;
                            }
                        }
                        DefaultCombineModel a = new DefaultCombineModel();
                        a.setId(cpu1.getId());
                        a.setPrice(cpu1.getPrice());
                        DefaultCombineModel b = new DefaultCombineModel();
                        b.setId(mb1.getId());
                        b.setPrice(mb1.getPrice());
                        set2[count][0] = a;
                        set2[count][1] = b;
                        count++;
                    }
                }
            }
        }
        //System.out.println(count);
        count = 0;
        DefaultCombineModel[][] set1 = new DefaultCombineModel[24][2];
        List<GPUCombineModel> power = getCombinePower(10000000);
        List<GPUCombineModel> gpu = getCombineGPU(10000000);
        gpu.add(0, new GPUCombineModel());
        for (GPUCombineModel gpu1 : gpu) {
            for (GPUCombineModel power1 : power) {

                if (gpu1.getPrice() < 400000) {
                    if (power1.getPrice() > 60000) {
                        continue;
                    }
                }
                if (gpu1.getPrice()>250000) {
                    if (power1.getPower() == 500) continue;
                }
                if (gpu1.getId() > 0) {
                    if (power1.getPower() >= gpu1.getPower()) {
                        if (power1.getPower() > gpu1.getPower() + 100) continue;
                        DefaultCombineModel a = new DefaultCombineModel();
                        a.setId(gpu1.getId());
                        a.setPrice(gpu1.getPrice());
                        DefaultCombineModel b = new DefaultCombineModel();
                        b.setId(power1.getId());
                        b.setPrice(power1.getPrice());
                        set1[count][0] = a;
                        set1[count][1] = b;
                        count++;
                    }
                } else {
                    if (power1.getPower() == 500) {
                        DefaultCombineModel a = new DefaultCombineModel();
                        a.setId(gpu1.getId());
                        a.setPrice(gpu1.getPrice());
                        DefaultCombineModel b = new DefaultCombineModel();
                        b.setId(power1.getId());
                        b.setPrice(power1.getPrice());
                        set1[count][0] = a;
                        set1[count][1] = b;
                        count++;
                    }
                }
            }
        }
        MultiValueMap<Integer,HashMap<Integer,Integer>> result = new LinkedMultiValueMap<>();
        String[] tmp1 = {"소켓1200", "소켓1700", "AM4", "AM5"};
        List<GPUCombineModel> gpu3 = getCombineGPU(10000000);
        gpu.add(0,new GPUCombineModel());
        for(int i=0;i<4;i++) {
            List<CPUCombineModel> cpu = getCombineCPU(10000000, tmp1[i]);
            for(CPUCombineModel cpu1:cpu){
                for(GPUCombineModel gpu1:gpu3){
                    if(gpu1==null||cpu1==null)continue;
                    if(cpu1.getId()==0||gpu1.getId()==0)continue;
                    String as = getBottleNeck(cpu1.getId(), gpu1.getId());
                    HashMap<Integer,Integer> res1=new HashMap<>();
                    int tmp2=as.indexOf(".");
                    if(tmp2==-1){
                        res1.put(gpu1.getId(),Integer.parseInt(as.substring(0, as.indexOf("%"))));
                    }
                    else {
                        res1.put(gpu1.getId(), Integer.parseInt(as.substring(0, as.indexOf("."))));
                    }
                    result.add(cpu1.getId(),res1);
                    //System.out.println(cpu1.getId()+" " + gpu1.getId()+" "+ as);
                }
            }
        }
        //System.out.println(count);
        count=0;
        int cash_count=0;
        int before_usedid=0;
        List<DefaultCombineModel> cooler = getCombineCooler(end);
        HashMap<Integer,Integer> sets=new HashMap<>();
        cooler.add(0, new DefaultCombineModel());
        List<DefaultCombineModel> cases = getCombineCase(end);
        List<RamCombineModel> ram = getCombineRam(end);
        List<DefaultCombineModel> ssd = getCombineSSD(end);
        List<DefaultCombineModel> hdd = getCombineHDD(end);
        hdd.add(0, new DefaultCombineModel());
        int res=0;
        for(DefaultCombineModel cooler1:cooler){
            for(DefaultCombineModel cases1:cases){
                for(DefaultCombineModel hdd1:hdd){
                    for(RamCombineModel ram1:ram){
                        for(DefaultCombineModel ssd1:ssd){
                            for (int i = 0; i < 43; i++) {
                                for (int j = 0; j < 24; j++) {
                                    res++;
                                    boolean isK=true;
                                    if(set1[j][0]==null)continue;
                                    if(set2[i][0]==null)continue;
                                    for(HashMap<Integer,Integer> e : result.get(set2[i][0].getId())){
                                        if(e.get(set1[j][0].getId())!=null) {
                                            if(e.get(set1[j][0].getId())>19){
                                                isK=false;
                                                break;
                                            }
                                        }
                                    }
                                    if(!isK)continue;
                                    if(set1[j][0].getPrice()>400000){
                                        if(set2[i][0].getPrice()<240000){
                                            continue;
                                        }
                                    }
                                    if(set1[j][0].getPrice()>240000){
                                        if(ssd1.getPrice()<55000)continue;
                                    }
                                    if(set1[j][0].getPrice()>500000){
                                        if(ssd1.getPrice()<100000)continue;
                                    }
                                    if(set1[j][0].getPrice()>0){
                                        if(cooler1.getPrice()==0)continue;
                                    }
                                    if(set2[i][0].getPrice()<450000){
                                        if(set1[j][0].getPrice()>1000000)continue;
                                    }
                                    if(set2[i][0].getPrice()<200000){
                                        if(set2[i][1].getPrice()>140000)continue;
                                    }
                                    if(set2[i][0].getPrice()>450000){
                                        if(set2[i][1].getPrice()<300000)continue;
                                    }
                                    if(set2[i][0].getPrice()>450000){
                                        if(set1[j][0].getPrice()<600000)continue;
                                    }
                                    int sum=cooler1.getPrice()+cases1.getPrice()+hdd1.getPrice()+(ram1.getPrice()*2)+ssd1.getPrice()+set1[j][0].getPrice()+set1[j][1].getPrice()+set2[i][0].getPrice()+set2[i][1].getPrice();

                                    //System.out.println(set1[j][0].getPrice()+set1[j][1].getPrice());
                                    if(sum<start)continue;
                                    if(sum>end)continue;
                                    if(sum>550000){
                                        if(set1[j][0].getPrice()==0)continue;
                                    }
                                    if(set1[j][0].getId()>0) {
                                        if (sets.containsKey(set1[j][0].getId())) {
                                            if (sets.get(set1[j][0].getId()) > 2) continue;
                                        }
                                        if (sets.containsKey(set1[j][0].getId()))
                                            sets.put(set1[j][0].getId(), sets.get(set1[j][0].getId()) + 1);
                                        else
                                            sets.put(set1[j][0].getId(), 1);
                                    }
                                    String map2="&productSeqList=";
                                    String map3="&quantityList=";

                                    JSONObject obj=new JSONObject();
                                    obj.put("cpu_id",set2[i][0].getId());
                                    map2+=set2[i][0].getId();
                                    map2+=",";
                                    obj.put("cpu_count",1);
                                    map3+="1,";
                                    obj.put("mainboard_id",set2[i][1].getId());
                                    obj.put("mainboard_count",1);
                                    map2+=set2[i][1].getId();
                                    map2+=",";
                                    map3+="1,";

                                    obj.put("gpu_id",set1[j][0].getId());
                                    if(set1[j][0].getId()!=0) {
                                        obj.put("gpu_count", 1);
                                        map2+=set1[j][0].getId();
                                        map2+=",";
                                        map3+="1,";
                                    }
                                    else {
                                        obj.put("gpu_count", 0);
                                    }

                                    obj.put("power_id",set1[j][1].getId());
                                    map2+=set1[j][1].getId();
                                    map2+=",";
                                    obj.put("power_count",1);
                                    map3+="1,";
                                    obj.put("ssd_id",ssd1.getId());
                                    map2+=ssd1.getId();
                                    map2+=",";
                                    obj.put("ssd_count",1);
                                    map3+="1,";
                                    obj.put("ram_id",ram1.getId());
                                    map2+=ram1.getId();
                                    map2+=",";
                                    obj.put("ram_count",2);
                                    map3+="2,";
                                    obj.put("hdd_id",hdd1.getId());
                                    if(hdd1.getId()!=0) {
                                        obj.put("hdd_count", 1);
                                        map2+=hdd1.getId();
                                        map2+=",";
                                        map3+="1,";
                                    }
                                    else
                                        obj.put("hdd_count",0);
                                    obj.put("case_id",cases1.getId());
                                    map2+=cases1.getId();
                                    map2+=",";
                                    map3+="1,";
                                    obj.put("case_count",1);
                                    obj.put("cooler_id",cooler1.getId());
                                    if(cooler1.getId()!=0) {
                                        map2+=cooler1.getId();
                                        map3+="1";
                                        obj.put("cooler_count", 1);
                                    }
                                    else
                                        obj.put("cooler_count",0);
                                    obj.put("total_price",sum);
                                    String total1=map_1+map2+map3;
                                    obj.put("total_link",total1);
                                    jsonTotal.add(obj);
                                    count++;
                                    if(count==5)return jsonTotal;
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println(res);
        return jsonTotal;
    }

}
