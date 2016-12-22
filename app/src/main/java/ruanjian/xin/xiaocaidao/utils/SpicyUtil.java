package ruanjian.xin.xiaocaidao.utils;

import ruanjian.xin.xiaocaidao.R;

/**
 * Created by xin on 2016/12/18.
 */

public class SpicyUtil {
    private static int img_baicai= R.drawable.baicai;
    private static int img_xiangxinliao= R.drawable.xiangxinliao;
    private static int img_bocai= R.drawable.bocai;
    private static int img_xihongshi= R.drawable.xihongshi1;
    private static int img_heihujiao= R.drawable.heihujiao;
    private static int img_baicu= R.drawable.baicu2;

    private static String name_baicai="大白菜";
    private static String name_xiangxinliao="香辛料";
    private static String name_bocai="菠菜";
    private static String name_xihongshi="西红柿";
    private static String name_heihujiao="黑胡椒";
    private static String name_baicu="白醋";

    private static String des_baicai="        白菜原产于我国北方，是十字花科芸薹属叶用蔬菜，通常指大白菜。引种南方，南北各地" +
            "均有栽培。十九世纪传入日本、欧美各国。大白菜种类很多，北方的大白菜有山东胶州大白菜、北京青白、天津青麻叶" +
            "大白菜、东北大矮白菜、山西阳城的大毛边等。";
    private static String des_xiangxinliao="        香辛料是利用植物的种子、花蕾、叶茎、花蕾、根块等，或其提取物，具有刺激性香" +
            "味，赋予食物以风味，增进食欲，帮助消化和吸收的作用。香辛料含有挥发油（精油）、辣味成分及有机酸、纤维、淀粉" +
            "粒、树脂、粘液物质、胶质等成分，其大部分香气来自蒸馏后的精油。香辛料广泛应用于烹饪食品和食品工业中，主要起" +
            "调香、调味、调色等作用。";
    private static String des_bocai="        菠菜（Spinacia oleracea L.）又名波斯菜、赤根菜、鹦鹉菜等[1]  ，属藜科菠菜属，一年" +
            "生草本植物。植物高可达1米，根圆锥状，带红色，较少为白色，叶戟形至卵形，鲜绿色，全缘或有少数牙齿状裂片。  菠" +
            "菜的种类很多，按种子形态可分为有刺种与无刺种两个变种。";
    private static String des_xihongshi="        番茄（学名：Lycopersicon esculentum Mill.），是茄科番茄属一年生或多年生草本" +
            "植物，体高0.6-2米，全体生粘质腺毛，有强烈气味，茎易倒伏，叶羽状复叶或羽状深裂，花序总梗长2-5厘米，常3-7朵" +
            "花，花萼辐状，花冠辐状，浆果扁球状或近球状，肉质而多汁液，种子黄色，花果期夏秋季。";
    private static String des_heihujiao="        黑胡椒（学名：Piper nigrum），学名为Piper nigrum，是胡椒科的一种开花藤本植物，" +
            "又名黑川，原产于印度马拉巴海岸(Malabar Coast)。其果味辛辣，是人们最早使用的香料之一，可能是现在使用最为广泛" +
            "的香料。" +
            "同样的果实还是白胡椒、红胡椒与绿胡椒的制作原料。黑胡椒原产于南印度，在当地和其他热带地区都有着广泛的种植。" +
            "黑胡椒的果实在熟透时会呈现黑红色，并包含一粒种子；果实在晒干后会成为直径5毫米的胡椒子核果." +
            "医药上也用作驱风药和用于刺激胃分泌。胡椒在东南亚热带地区有悠久而广泛的栽培史，早就被视为优良品。是印度" +
            "和欧洲之间的重要贸易商品，又是交换的媒介。在古希腊和罗马还征集胡椒作为贡品。在中世纪，威尼斯人和热那亚" +
            "人垄断了胡椒贸易，从而促使人们寻找到远东的航路。";
    private static String des_baicu="        白醋是烹调的酸味辅料，色泽透亮、酸味醇正。除了3-5%醋酸和水之外不含或极少含" +
            "其他成分。以蒸馏过的酒发酵制成，或直接用食品级别的醋酸兑制。 无色，味道单纯。用于烹调，腌制酸辣" +
            "菜、酸萝卜等风味小吃，也可用做家用清洁剂，例如清洗咖啡机内部的积垢。";

    private static String natrition_baicai="        白菜除作为蔬菜供人们食用之外，还有药用价值。祖国医学认为，白菜性" +
            "味甘平，有清热除烦、解渴利尿、通利肠胃的功效，经常吃白菜可防止维生素C缺乏症（坏血病）。民间还有用" +
            "白菜治感冒的验方，其方法是用白菜干根加红糖、姜片、水煎服，或用白菜根三个，大葱根七个，煎水加红糖，" +
            "趁热饮服，盖被出汗，感冒即愈。大白菜洗净切碎煎浓汤，每晚睡前洗冻疮患处，连洗数日即可风效。白菜子" +
            "则可解酒，对于酒醉不醒者，可用白菜子研末调“井华水”（即从水井中刚打上来的井水），服之有效。对于" +
            "气虚胃冷的人，则不宜多吃白菜，以免恶心吐沫。若吃多了，可用生姜解之。白菜能降低女性乳腺癌发生率。" +
            "美国纽约激素研究所的科学家发现，中国和日本妇女乳腺癌率之所以比西方妇女低得多，是由于她们常吃白菜" +
            "的缘故。白菜中有一些微量元素，它们能帮助分解同乳腺癌相联系的雌激素。白菜中的纤维素不但能起到润肠、" +
            "促进排毒的作用，还能促进人体对动物蛋白质的吸收。中医认为白菜微寒味甘，有养胃生津、除烦解渴、利尿通便" +
            "、清热解毒之功。";
    private static String natrition_xiangxinliao="        公元前1世纪的《神农本草经》中将中草药分为上、中、下三品，上药应天之命，与神相通.能补养生息，无毒，长期服用无害，有延年益寿、轻身益气之功效，其中主要者为桂皮、人参、甘草和察香;中药指有养生防病.滋补体力，充分利用其特点调整其毒性，可配合使用者，主要为生姜、当归、犀角等;下药主治各种疾病，因有毒性忌长期服用，主要的有大黄、桔梗、杏仁等。这说明中国对辛香料的应用有着非常久远的历史和科学的认识。中国菜扬名于天下与其巧妙地发挥辛香料的独特风味和诱食性不无关系，这也是中国烹饪的一大特色。\n" +
            "食用辛香料是人类最早交易项目之一也是古代文明进化史的重要组成部分。东西方的文化交流，亦自辛香料交易开始。南宋赵汝南著的《诸蕃志》中，就将丁香、胡椒与珍珠、玛瑙并驾齐驱地列为国际贸易商品。福建泉州为世界闻名的海上丝绸之路，同时也是香料之路的起点，20世纪70年代在泉州发掘的宋代沉船中发现大量的香料，其中一大部分是辛香料。";
    private static String natrition_bocai="    养血，止血，敛阴，润燥。治衄血，便血，坏血病，消渴引饮，大便涩滞。[12] \n" +
            "1、通肠导便，防治痔疮\n" +
            "　　菠菜含有大量的植物粗纤维，具有促进肠道蠕动的作用，利于排便，且能促进胰腺分泌，帮助消化。对于痔疮、慢性胰腺炎、便秘、肛裂等病症有治疗作用。[13]  \n" +
            "　　2、促进生长发育，增强抗病能力\n" +
            "　　菠菜中所含的胡萝卜素，在人体内转变成维生素A，能维护正常视力和上皮细胞的健康，增加预防传染病的能力，促进儿童生长发育。[13]  \n" +
            "　　3、保障营养，增进健康\n" +
            "　　菠菜中含有丰富的胡萝卜素、维生素C、钙、磷，及一定量的铁、维生素E、芸香贰、辅酶Q10等有益成分，能供给人体多种营养物质；其所含铁质，对缺铁性贫血有较好的辅助治疗作用。[13]  \n" +
            "　　4、促进人体新陈代谢，延缓衰老\n" +
            "　　菠菜中的含氟-生齐酚、6-羟甲基蝶陡二酮及微量元素物质，能促进人体新陈代谢，增进身体健康。大量食用菠菜，可降低中风的危险。[13]  \n" +
            "　　5、洁皮肤，抗衰老\n" +
            "　　菠菜提取物具有促进培养细胞增殖作用，既抗衰老又增强青春活力。中国民间以菠菜捣烂取汁，每周洗脸数次，连续使用一段时间，可清洁皮肤毛孔，减少皱纹及色素斑，保持皮肤光洁。[13]  \n" +
            "　　6、补血\n" +
            "　　菠菜的蛋白质量高于其他蔬菜，且含有相当多的叶绿素，尤其含维生素K在叶菜类中最高(多含于根部)，能用于鼻出血、肠出血的辅助治疗。菠菜补血之理与其所含丰富的类胡萝卜素、抗坏血酸有关，两者对身体健康和补血都有重要作用。";
    private static String natrition_xihongshi="        西红柿有生津止渴、健胃消食、清热消暑、补肾利尿等功能，可治热病伤津口渴、食欲不振、暑热内盛等病症。它有显著止血、降压、降低胆固醇作用，对治疗血友病和癞皮病有特殊功效。\n" +
            "　　多吃西红柿护心脏，美国科学家的一项研究表明，用农家肥种植出的有机西红柿具有保护心脏的作用。有机西红柿中的抗氧化剂类黄酮的含量，是普通西红柿含量的两倍。类黄酮具有降血压、降血脂、增加冠脉血流量等功能。\n" +
            "　　西红柿越红，营养越高，做熟后比生吃更有利于营养吸收。\n" +
            "　　西红柿中主要的营养就是维生素，其中，最重要、含量最多的就是胡萝卜素中的一种——番茄红素。科学家们对番茄红素健康作用的研究有了很多新的突破，已经证明的包括：具有独特的抗氧化能力，可以清除人体内导致衰老和疾病的自由基；预防心血管疾病的发生；阻止前列腺的癌变进程，并有效地减少胰腺癌、直肠癌、喉癌、口腔癌、乳腺癌等癌症的发病危险。\n" +
            "现代医学研究表明，癌症病人对维生素C的需要量显著增加，而西红柿含有丰富的维生素C，是防癌抗癌的首选果蔬。\n" +
            "西红柿是维生素C的天然食物来源，每天食用1-2个西红柿，可以增强血管柔韧性，制止牙龈出血，增强抗癌能力，对高血压、心脏病患者非常有益。同时，西红柿中还含有一种特殊成分――番茄素，而番茄素具有止渴生津、健胃消食的作用，可防治胃热口苦、发热烦渴、中暑等症，是益气生津、健脾和胃的佳品。\n" +
            "　　餐前吃西红柿容易使胃酸增高，食用者会产生烧心、腹痛等不适症状。而餐后吃西红柿，由于胃酸已经与食物混合，胃内酸度会降低，就能避免出现这些症状。";
    private static String natrition_heihujiao="        胡椒不仅是常用的调味料，还是一味中药。胡椒味辛，大温，无毒。" +
            "入胃、大肠经。有温中，下气，消痰，解毒等功效。主治治寒痰食积，脘腹冷痛，反胃，呕吐清水，泄泻，冷痢" +
            "。另外，胡椒有黑胡椒和白胡椒之分，其功效并不完全相同。    黑胡椒是在果实开始变红时采收晒干的未去皮" +
            "者，白胡椒是果实全部变红时采收，经水浸去皮晒干而成。白胡椒散寒、健胃功效更强，肺寒痰多者可用羊肉200" +
            "克煮汤后加白胡椒粉2克，连汤食用可温肺化痰。黑胡椒温补脾肾功效明显，可治疗由脾肾虚寒造成的五更泻，即" +
            "食用鸡汤加黑胡椒2克，可起散寒止泻作用。";
    private static String natrition_baicu="1. 醋可以开胃，促进唾液和胃液的分泌，帮助消化吸收，使食欲旺盛，消食化积；\n" +
            "2. 醋有很好的抑菌和杀菌作用，能有效预防肠道疾病、流行性感冒和呼吸疾病；\n" +
            "3. 醋可软化血管、降低胆固醇，是高血压等心脑血管疾病患者的一剂良方；\n" +
            "4. 醋对皮肤、头发能起到很好的保护作用，中国古代医学就有用醋入药的记载，认为它有生发、美容、降压、减肥的功效；\n" +
            "5. 醋可以消除疲劳，促进睡眠，并能减轻晕车、晕船的不适症状；\n" +
            "6. 醋还能减少胃肠道和血液中的酒精浓度，起到醒酒的作用；\n" +
            "7. 醋还有使鸡骨、鱼刺等骨刺软化的作用，从而促进钙的吸收。";

    public int getImage(int type){
        switch (type){
            case 1:
                return img_baicai;
            case 2:
                return img_xiangxinliao;
            case 3:
                return img_bocai;
            case 4:
                return img_xihongshi;
            case 5:
                return img_heihujiao;
            case 6:
                return img_baicu;
            default:
                return img_baicai;
        }
    }
    public String getName(int type){
        switch (type){
            case 1:
                return name_baicai;
            case 2:
                return name_xiangxinliao;
            case 3:
                return name_bocai;
            case 4:
                return name_xihongshi;
            case 5:
                return name_heihujiao;
            case 6:
                return name_baicu;
            default:
                return "";
        }
    }

    public String getNatrition(int type){
        switch (type){
            case 1:
                return natrition_baicai;
            case 2:
                return natrition_xiangxinliao;
            case 3:
                return natrition_bocai;
            case 4:
                return natrition_xihongshi;
            case 5:
                return natrition_heihujiao;
            case 6:
                return natrition_baicu;
            default:
                return "";
        }
    }

    public String getDes(int type){
        switch (type){
            case 1:
                return des_baicai;
            case 2:
                return des_xiangxinliao;
            case 3:
                return des_bocai;
            case 4:
                return des_xihongshi;
            case 5:
                return des_heihujiao;
            case 6:
                return des_baicu;
            default:
                return "";
        }
    }
}
