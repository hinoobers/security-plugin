package org.hinoob.security.util;

import java.util.List;

public class LocaleUtil {

    private static final List<String> locales =
            List.of("af_za", "ar_sa", "as_in", "az_az", "be_by", "bg_bg", "bn_bd", "bs_ba", "ca_es", "cs_cz", "cy_gb",
                    "da_dk", "de_at", "de_ch", "de_de", "el_gr", "en_au", "en_ca", "en_gb", "en_ie", "en_in", "en_nz",
                    "en_ph", "en_sg", "en_us", "en_za", "es_ar", "es_bo", "es_cl", "es_co", "es_cr", "es_do", "es_ec",
                    "es_es", "es_gt", "es_hn", "es_mx", "es_ni", "es_pa", "es_pe", "es_pr", "es_py", "es_sv", "es_us",
                    "es_uy", "es_ve", "et_ee", "eu_es", "fa_ir", "fi_fi", "fil_ph", "fo_fo", "fr_be", "fr_ca", "fr_ch",
                    "fr_fr", "fr_lu", "fr_mc", "gl_es", "gsw_fr", "gu_in", "he_il", "hi_in", "hr_hr", "hu_hu", "hy_am",
                    "id_id", "is_is", "it_ch", "it_it", "ja_jp", "ka_ge", "kk_kz", "km_kh", "kn_in", "ko_kr", "ky_kg",
                    "lo_la", "lt_lt", "lv_lv", "mi_nz", "mk_mk", "ml_in", "mn_mn", "mr_in", "ms_my", "mt_mt", "nb_no",
                    "ne_np", "nl_be", "nl_nl", "nn_no", "or_in", "pa_in", "pl_pl", "ps_af", "pt_br", "pt_pt", "ro_ro",
                    "ru_ru", "ru_ua", "si_lk", "sk_sk", "sl_si", "sq_al", "sr_ba", "sr_cs", "sr_me", "sr_rs", "sv_se",
                    "sw_ke", "ta_in", "te_in", "th_th", "tk_tm", "tr_tr", "tt_ru", "uk_ua", "ur_pk", "uz_uz", "vi_vn",
                    "wo_sn", "zh_cn", "zh_hk", "zh_tw", "zlm_ara", "val_es", "tlh_aa", "enp"); // Might not contain everything

    public static boolean exists(String locale) {
        return locales.contains(locale);
    }
}
